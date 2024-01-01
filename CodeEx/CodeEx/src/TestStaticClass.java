public class TestStaticClass {

      static class StaticNestedClass {
         static void run() {
          walk();
            

        }
    }

    class InnerClass {

          void nestedMethod() {
              StaticNestedClass.run();
              walk();
              eat();
          }
    }

    public static void walk() {

    }

    public static void walk(String name) {

    }

    public  void eat() {

    }
}
