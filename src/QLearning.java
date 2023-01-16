
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class QLearning {

    private final double alpha = 0.1; // ogrenme katsayısı
    private final double gamma = 0.8; 

    private  int mazeWidth ;
    private  int mazeHeight ;
    private  int statesCount ;

    private final int odul = 100;
    private final int ceza = -10;
    

    private int[][] Matris;
    
    private int[][] R;       
    private double[][] Q;    

    QLearning(int[][] Matris,int cols,int rows) {
 this.Matris = Matris;
 this.mazeHeight=cols;
 this.mazeWidth=rows;
 this.statesCount = cols*rows;
    }
QLearning(){}


    public void init(int Matris[][]) {
        

        R = new int[statesCount][statesCount];
        Q = new double[statesCount][statesCount];
      


       

            int i = 0;
            int j = 0;
            int content;


            for (int k = 0; k < statesCount; k++) {

               
                i = k / mazeWidth;
                j = k - i * mazeWidth;

               
                for (int s = 0; s < statesCount; s++) {
                    R[k][s] = -1;
                }

                
                if (Matris[i][j] != 99 || Matris[i][j] != 1) {

                    
                    int sol = j - 1;
                    if (sol >= 0) {
                        int target = i * mazeWidth + sol;
                        if (Matris[i][sol] == 0 || Matris[i][sol] == 2 || Matris[i][sol] == 88 ) {
                            R[k][target] = 0;
                        } else if (Matris[i][sol] == 99) {
                            R[k][target] = odul;
                        } else {
                            R[k][target] = ceza;
                        }
                    }

                    
                    int sag = j + 1;
                    if (sag < mazeWidth) {
                        int target = i * mazeWidth + sag;
                        if (Matris[i][sag] == 2 || Matris[i][sag] == 0 || Matris[i][sag] == 88) {
                            R[k][target] = 0;
                        } else if (Matris[i][sag] == 99) {
                            R[k][target] = odul;
                        } else {
                            R[k][target] = ceza;
                        }
                    }

                    
                    int yukari = i - 1;
                    if (yukari >= 0) {
                        int target = yukari * mazeWidth + j;
                        if (Matris[yukari][j] == 2 || Matris[yukari][j] == 0 || Matris[i][yukari] == 88) {
                            R[k][target] = 0;
                        } else if (Matris[yukari][j] == 99) {
                            R[k][target] = odul;
                        } else {
                            R[k][target] = ceza;
                        }
                    }

                  
                    int assagi = i + 1;
                    if (assagi < mazeHeight) {
                        int target = assagi * mazeWidth + j;
                        if (Matris[assagi][j] == 2 || Matris[assagi][j] == 0 || Matris[i][assagi] == 88  ) {
                            R[k][target] = 0;
                        } else if (Matris[assagi][j] == 99) {
                            R[k][target] = odul;
                        } else {
                            R[k][target] = ceza;
                        }
                    }
                }
            }
            initializeQ();
          //  printR(R); // cıkarılabilir 
       
    }
  
    void initializeQ()
    {
        for (int i = 0; i < statesCount; i++){
            for(int j = 0; j < statesCount; j++){
                Q[i][j] = (double)R[i][j];
            }
        }
    }
    
    void printR(int[][] matrix) {
        System.out.printf("%25s", "Durumlar: ");
        for (int i = 0; i <= 8; i++) {
            System.out.printf("%4s", i);
        }
        System.out.println();

        for (int i = 0; i < statesCount; i++) {
            System.out.print("Durum " + i + " :[");
            for (int j = 0; j < statesCount; j++) {
                System.out.printf("%4s", matrix[i][j]);
            }
            System.out.println("]");
        }
    }

    void hesaplaQ() {
        Random rand = new Random();

        for (int i = 0; i < 1000; i++) { // 1000 deneme yapılmaktadır
           
            int crtState = rand.nextInt(statesCount);

            while (!isFinalState(crtState)) {
                int[] actionsFromCurrentState = olasiAksiyonlar(crtState);

                
                int index = rand.nextInt(actionsFromCurrentState.length);
                int nextState = actionsFromCurrentState[index];

                // Q(state,action)= Q(state,action) + alpha * (R(state,action) + gamma * Max(next state, all actions) - Q(state,action))
                double q = Q[crtState][nextState];
                double maxQ = maxQ(nextState);
                int r = R[crtState][nextState];

                double value = q + alpha * (r + gamma * maxQ - q);
                Q[crtState][nextState] = value;

                crtState = nextState;
            }
        }
    }

    boolean isFinalState(int state) {
        int i = state / mazeWidth;
        int j = state - i * mazeWidth;

        return Matris[i][j] == 99;
    }

    int[] olasiAksiyonlar(int state) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < statesCount; i++) {
            if (R[state][i] != -1) {
                result.add(i);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    double maxQ(int nextState) {
        int[] actionsFromState = olasiAksiyonlar(nextState);
  
        double maxValue = -10;
        for (int nextAction : actionsFromState) {
            double value = Q[nextState][nextAction];

            if (value > maxValue)
                maxValue = value;
        }
        return maxValue;
    }

    void printPolicy() {
        System.out.println("\nGuzergah");
        for (int i = 0; i < statesCount; i++) {
            System.out.println("Durum= " + i + " -----> " + sonrakiDurum(i));
        }
    }

    int sonrakiDurum(int state) {
        int[] actionsFromState = olasiAksiyonlar(state);

        double maxValue = Double.MIN_VALUE;
        int policyGotoState = state;

   
        for (int nextState : actionsFromState) {
            double value = Q[state][nextState];

            if (value > maxValue) {
                maxValue = value;
                policyGotoState = nextState;
            }
        }
        return policyGotoState;
    }

    void printQ() {
        System.out.println("Q matrix");
        for (int i = 0; i < Q.length; i++) {
            System.out.print("Durum " + i + ":  ");
            for (int j = 0; j < Q[i].length; j++) {
                System.out.printf("%6.2f ", (Q[i][j]));
            }
            System.out.println();
        }
    }
}
