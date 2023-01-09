package org.cloudbus.cloudsim.examples;

import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Vm;

//import ssa.Algorithm;
//import ssa.Param;

public class Algorithm {
	 List<Vm> N;
	 List<Cloudlet> D;
	 double PDP;
	 double DG;
	 double GC;
	 int MAX_ITER;
	 double LAMBDA;
	 int FSU;
	 int FSL;

	public static List<Cloudlet> cloudletList;
	public static List<Vm> vmlist;
	
	@SuppressWarnings("static-access")
	public Algorithm(int N, int D, double PDP, double DG, double GC, int MAX_ITER, double LAMBDA, int FSU, int FSL, List<Cloudlet> cloudletList, List<Vm> vmlist) {
		this.N = vmlist;
		this.D = cloudletList;
		this.PDP = PDP;
		this.DG = DG;
		this.GC = GC;
		this.MAX_ITER = MAX_ITER;
		this.LAMBDA = LAMBDA;
		this.FSU = FSU;
		this.FSL = FSL;
		this.cloudletList = cloudletList;
		this.vmlist = vmlist;
	}

    //membangkitkan populasi squirrel terbang awal
    public void population(Integer N, Integer D, Integer FSU, Integer FSL) {
        Double iterasi[] = new Double[MAX_ITER];
        for (int iter = 0; iter < MAX_ITER; iter++) {
            System.out.println("\n===================INISIALISASI POPULASI===================\n");
            Double[][] squirrel = new Double[N][D];
            Double[][] squirrel1 = new Double[N][D];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < D; j++) {
                    squirrel[i][j] = (FSL + (Math.random() * ((FSU - FSL))));
                }
            }

            for (Integer i = 0; i < N; i++) {
                for (Integer j = 0; j < D; j++) {
                    System.out.print(squirrel[i][j] + " ");
                }
                System.out.println();
            }
            
            System.out.println("\n===================NILAI TERBESAR===================\n");
            Integer[][] order = Algorithm.order(N, squirrel);
//            order(squirrel, D, N);
//            //Integer[][] x = new Integer[N][D];
//            //pengurutan nilai terkecil ke terbesar
//            for (Integer i = 0; i < N; i++) {
//              for (Integer j = 0; j < D; j++) {
//				System.out.print(squirrel[i][j] + "");
//              }
//              System.out.println();
//           }
            System.out.println("\n===================NILAI TERBESAR===================\n");
            
            //System.out.println("\n===================PENEMPATAN===================\n");
            //Integer[][] x = Algorithm.replace(Param.D, Param.N, order);
//            Integer[][] x = new Integer[N][D];
//            penempatan(squirrel, N, D, x);
//            
//            for (Integer i = 0; i < N; i++) {
//                for (Integer j = 0; j < D; j++) {
//                    System.out.print(x[i][j] + " ");
//                }
//                System.out.println();
//            }

            System.out.println("\n===================PANGKAT===================\n");

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < D; j++) {
                    squirrel1[i][j] = Math.pow(squirrel[i][j], 2);
                }
            }

            for (Integer i = 0; i < N; i++) {
                for (Integer j = 0; j < D; j++) {
                    System.out.print(squirrel1[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("\n===================PANGKAT===================\n");

            System.out.println("\n===================CALCULATE FITNESS===================\n");
            Double sum[][] = new Double[2][N];
            for (int i = 0; i < N; i++) {
                sum[0][i] = Double.valueOf(i + 1);
                sum[1][i] = 0.0;
                for (int j = 0; j < D; j++) {
                    sum[1][i] += squirrel1[i][j];
                }
                System.out.println("fitness FS " + sum[0][i].intValue() + " adalah: " + sum[1][i]);
            }
            System.out.println("\n===================CALCULATE FITNESS===================\n");

            System.out.println("\n===================ORDER===================\n");

            sort(sum, N);
            String tree;
            for (int i = 0; i < N; i++) {
                if (i == 0) {
                    tree = "Hickory Nut Tree";
                } else if (i >= 1 && i <= 3) {
                    tree = "Acorn  Nut Tree";
                } else {
                    tree = "Normal Tree";
                }
                System.out.println("fitness FS " + sum[0][i].intValue() + " adalah: " + sum[1][i] + " " + tree);
            }
            System.out.println("\n===================ORDER===================\n");

            System.out.println("\n===================MODIFIKASI TUPAI TERBANG===================\n");

            Double newsquirrel[][] = new Double[N][D];

            for (Integer i = 0; i < N; i++) {
                for (Integer j = 0; j < D; j++) {
                    newsquirrel[i][j] = squirrel[sum[0][i].intValue() - 1][j];
                }
            }

//        for (Integer i = 0; i < 1; i++) {
//            for (Integer j = 0; j < D; j++) {
//                System.out.print(squirrel[sum[0][i].intValue() - 1][j] + " ");
//            }
//            System.out.println();
//        }
            for (Integer i = 0; i < 1; i++) {
                for (Integer j = 0; j < D; j++) {
                    System.out.print(newsquirrel[i][j] + " ");
                }
                System.out.println();
            }

//        for (Integer i = 1; i < 4; i++) {
//            for (Integer j = 0; j < D; j++) {
//                System.out.print(squirrel[sum[0][0].intValue() - 1][j] + Param.DG * Param.GC * (squirrel[sum[0][i].intValue() - 1][j] - squirrel[sum[0][0].intValue() - 1][j]) + " ");
//            }
//            System.out.println();
//        }
            for (Integer i = 1; i < 4; i++) {
                for (Integer j = 0; j < D; j++) {
                    System.out.print(newsquirrel[0][j] + DG * GC * (newsquirrel[i][j] - newsquirrel[0][j]) + " ");
                }
                System.out.println();
            }

//        for (Integer i = 3; i < N; i++) {
//            for (Integer j = 0; j < D; j++) {
//                System.out.print(squirrel[sum[0][0].intValue() - 1][j] + Param.DG * Param.GC * (squirrel[sum[0][i].intValue() - 1][j] - squirrel[sum[0][0].intValue() - 1][j]) + " ");
//            }
//            System.out.println();
//        }
            for (Integer i = 3; i < N; i++) {
                for (Integer j = 0; j < D; j++) {
                    Double k = Math.random();
                    if (k <= 0.5) {
                        System.out.print(newsquirrel[0][j] + DG * GC * (squirrel[2][j] - squirrel[i][j]) + " ");
                    } else {
                        System.out.print(newsquirrel[0][j] + DG * GC * (squirrel[1][j] - squirrel[j][j]) + " ");
                    }
                }
                System.out.println();
            }

            System.out.println("\n===================MODIFIKASI TUPAI TERBANG===================\n");

            System.out.println("\n===================CALCULATION UPDATE CONSTANTA===================\n");
            Double[] season = new Double[2];
//            Integer iterasi = 0;
            Integer a = -6;
            Double b = MAX_ITER / 2.5;
//            if (iterasi <= 3) {
            Double sc1 = 0.0;
            for (Integer i = 0; i < D; i++) {
                for (Integer j = 0; j < i; j++) {
                    sc1 += (Math.pow(newsquirrel[i][j] - newsquirrel[1][j], 2));
                }
            }
            season[0] = Math.sqrt(sc1);
            //10e^-6/365^iterasi/max_iter/2.5
            season[1] = (Math.pow(Math.exp(10), a)) / (Math.pow(365, b));
//            }

            for (Double s : season) {
                System.out.println(s + " ");
            }
            System.out.println("\n===================CALCULATION UPDATE CONSTANTA===================\n");

            System.out.println("\n===================LEVY===================\n");
            Double[] u = new Double[D];
            Double[] v = new Double[D];
            Double[] s = new Double[D];
            Double[] l = new Double[D];
//        Double r = null;
            for (Integer i = 0; i < N; i++) {
                for (Integer j = 0; j < D; j++) {

                    u[j] = Math.random();
                    v[j] = Math.random();

                    Double num = gamma(1.0 + LAMBDA) * Math.sin(Math.PI * LAMBDA / 2); //pembilang
                    Double den = gamma((1.0 + LAMBDA) / 2.0) * LAMBDA * Math.pow(2.0, ((LAMBDA - 1.0) / 2.0)); //penyebut
                    Double r = num / den;

                    s[j] = u[j] / (Math.pow(Math.abs(v[j]), LAMBDA)); //U/|V|^1/2
                    l[j] = (LAMBDA * gamma(LAMBDA) * Math.sin(Math.PI * LAMBDA / 2) / Math.PI) * 1 / (Math.pow(s[j], 1 + LAMBDA)); //nilai levy
                    if (season[0] < season[1]) {
                        newsquirrel[i][j] = FSL + l[j] * (FSU - FSL);
                    } else {
                        newsquirrel[i][j] = newsquirrel[i][j];
                    }
                }
            }

            for (Integer i = 0; i < N; i++) {
                for (Integer j = 0; j < D; j++) {
                    System.out.print(newsquirrel[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("\n===================LEVY===================\n");

            System.out.println("\n===================UPDATE FS===================\n");
            for (Integer i = 0; i < N; i++) {
                for (Integer j = 0; j < D; j++) {
                    squirrel[i][j] = newsquirrel[i][j];
                }
            }

            for (Integer i = 0; i < N; i++) {
                for (Integer j = 0; j < D; j++) {
                    System.out.print(squirrel[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("\n===================UPDATE FS===================\n");

            System.out.println("\n===================NILAI FITNESS===================\n");

            
            for (int i = 0; i <= iter; i++) {
                iterasi[iter] = sum[1][i];
            }
            for (int i = 0; i <= iter; i++) {
                System.out.println(iterasi[i] + " ");
            }

            System.out.println("\n===================NILAI FITNESS===================\n");
        }

    }

    private void sort(Double[][] sum, Integer N) {
        int n = N;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (sum[1][j] < sum[1][min_idx]) {
                    min_idx = j;
                }
            }

            Double temp0 = sum[0][min_idx];
            sum[0][min_idx] = sum[0][i];
            sum[0][i] = temp0;
            Double temp1 = sum[1][min_idx];
            sum[1][min_idx] = sum[1][i];
            sum[1][i] = temp1;
        }
    }
    
    public static Integer[][] order(Integer D, Double[][] squirrel){
    //private void order (Double[][] squirrel, Integer D, Integer N) {
	int n = D;
   //pengurutan nilai terkecil ke terbesar (kolom)
	for (int j = 0; j < n; j++) {
		for (int i = 0; i < n; i++) {
			for (int k = i+1; k < n; k++) {
				if (squirrel[i][j]>squirrel[k][j])
				{
					Double t = squirrel[i][j];
					squirrel[i][j]=squirrel[k][j];
					squirrel[k][j] = t;
				}
				
			}
			
		}
	}
	 
	for (Integer i = 0; i < n; i++) {
        for (Integer j = 0; j < n; j++) {
            System.out.print(squirrel[i][j] + " ");
        }
        System.out.println();
    }
	
	System.out.println();
	return null;
    }
    
     public static Integer[][] replace(Integer D, Integer N, Integer[][] order) { 
    	Integer[][] x = new Integer[N][D];
	    for (int j = 0; j < D; j++) {
	        //double max = Double.MIN_VALUE; // initialize max to the smallest possible double value
	        for (int i = 0; i < N; i++) {
	        	for (int k = 0; k < N; k++) {
	        		if (order[i][k].equals(i)) {
                        x[j][k] = 1;
                    } else {
                        x[j][k] = 0;
                    }
		            
	        	}
	        }
	        
	    }
	    
	    for (Integer i = 0; i < N; i++) {
            for (Integer j = 0; j < D; j++) {
                System.out.print(x[i][j] + " ");
            }
            System.out.println();
        }
		return x;
	
    }
    
//    private void penempatan(Double[][] squirrel, Integer N, Integer D, Integer[][] order) {
//    	Integer[][] x = new Integer[N][D];
//    	for (int j = 0; j < N; j++) {
//			for (int i = 0; i < D; i++) {
//    			for (int k = 0; k < D; k++) {
//    				if (order[i][k].equals(j)) {
//                        x[j][k] = 1;
//    				} else {
//                        x[j][k] = 0;
//                    }
//    				
//    			}
//    			
//    		}
//    	}
//    }
    
    private Double logGamma(Double x) {
        Double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        Double ser = 1.0 + 76.18009173 / (x + 0) - 86.50532033 / (x + 1)
                + 24.01409822 / (x + 2) - 1.231739516 / (x + 3)
                + 0.00120858003 / (x + 4) - 0.00000536382 / (x + 5);
        return tmp + Math.log(ser * Math.sqrt(2 * Math.PI));
    }

    private Double gamma(Double x) {
        return Math.exp(logGamma(x));
    }

}
