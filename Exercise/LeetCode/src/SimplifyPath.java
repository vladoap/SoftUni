import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.stream.Collectors;

public class SimplifyPath {

    public static void main(String[] args) {

        String path = "/../";

        System.out.println(simplifyPath(path));
    }

    public static String simplifyPath(String path) {

        String[] pathArray = path.split("[/]+");

        Deque<String> stack = new ArrayDeque<>();

        stack.push("/");

        for (int i = 1; i < pathArray.length; i++) {
            switch (pathArray[i]) {
                case ".", "/":
                break;
                case "..":
                    if (stack.size() > 2) {
                        stack.pop();
                        stack.pop();
                    }
                    break;
                default:
                    stack.push(pathArray[i]);
                    if (i < pathArray.length - 1) {
                        stack.push("/");
                    }
                break;

                }
            }

        if (stack.peek().equals("/") && stack.size() > 1) {
            stack.pop();
        }


        String[] result = new String[stack.size()];

        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        String stringResult = String.join("", result);

        return stringResult;
        }
    }

