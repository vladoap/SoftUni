public class Search2dMatrix {

    public static void main(String[] args) {
       int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 3;
        System.out.println(searchMatrix(matrix, target));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {

        for (int row = 0; row < matrix.length; row++) {
            int lastIndex = matrix[row].length - 1;
            if (target <= matrix[row][lastIndex]) {
                int[] arrayToSearch = matrix[row];
                int start = 0;
                int end = arrayToSearch.length - 1;

                while (start <= end) {
                    int mid = start + (end - start) / 2;
                    if (arrayToSearch[mid] == target) {
                        return true;
                    } else if (arrayToSearch[mid] < target) {
                        start = mid + 1;
                    } else if (arrayToSearch[mid] > target) {
                        end = mid - 1;
                    }
                }

            }
        }

        return false;
    }
}
