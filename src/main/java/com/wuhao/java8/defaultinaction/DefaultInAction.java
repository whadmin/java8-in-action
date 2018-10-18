package com.wuhao.java8.defaultinaction;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/5 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class DefaultInAction {


    public static void main(String[] args) {
        C c = new C();
        c.hello();
    }


    private interface A {

        default void hello() {
            System.out.println("==A.hello==");
        }
    }

    private interface B {

        default void hello() {
            System.out.println("==B.hello==");
        }
    }

    private static class C implements A, B {

        @Override
        public void hello() {
            B.super.hello();
        }
    }
}
