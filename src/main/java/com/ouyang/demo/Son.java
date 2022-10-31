package com.ouyang.demo;

public class Son extends Father{


    public void test(int one,String two){

    }


    public void test(String two,int one){

    }

    public static void main(String[] args) {
        TestInt<Son> getWeight = TestIntImpl::getWeight;
    }

    static class Fu {
        int a = 10;
        static int FU_STATIC_A = 10;
        public void printA() {
            System.out.println("Fu PrintA:" + a);
        }

        public Fu() {
            printA();
        }
    }

    static class Zi extends Fu {
        int a = 20;


        static {
            FU_STATIC_A = 25;
        }
        static int FU_STATIC_A = 20;
        @Override
        public void printA() {
            System.out.println("Zi PrintA:" + a);
            System.out.println("Zi PrintA FU_STATIC_A:" + FU_STATIC_A);
        }

        public Zi() {
            super();
            printA();
        }
    }
}
