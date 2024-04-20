import java.util.HashSet;
import java.util.Scanner;

class TicTacToe
{
    static HashSet<Integer> user_set = new HashSet<Integer>();
    static HashSet<Integer> computer_set = new HashSet<Integer>();
    public static void main(String args[])
    {
        char [][] game_board={
                {' ', '|', ' ', '|', ' '},
                {'-', '|', '-', '|', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '|', '-', '|', '-'},
                {' ', '|', ' ', '|', ' '}
        };
        print_board(game_board);
        Scanner input = new Scanner(System.in);
        while(true)
        {
            System.out.print("Enter values between 1 and 9: ");
            int user_position = input.nextInt();
            while(user_set.contains(user_position) || computer_set.contains(user_position))
            {
                System.out.println();
                System.out.print("Retry entering values between 1 and 9: ");
                user_position = input.nextInt();
            }
            p_holder(game_board, user_position, "You");

            String result = check_winner();
            if (result.length() > 0)
            {
                System.out.println(result);
                break;
            }


            int cpu_position = generate_random();
            while(user_set.contains(cpu_position) || computer_set.contains(cpu_position))
            {
                cpu_position = generate_random();
            }
            p_holder(game_board, cpu_position, "CPU");

            result = check_winner();
            if (result.length() > 0)
            {
                System.out.println(result);
                break;
            }
        }
    }

    static String check_winner()
    {
        HashSet<Integer> r1 = new HashSet<Integer>();
        r1.add(1); r1.add(2); r1.add(3);
        HashSet<Integer> r2 = new HashSet<Integer>();
        r2.add(4); r2.add(5); r2.add(6);
        HashSet<Integer> r3 = new HashSet<Integer>();
        r3.add(7); r3.add(8); r3.add(9);
        HashSet<Integer> c1 = new HashSet<Integer>();
        c1.add(1); c1.add(4); c1.add(7);
        HashSet<Integer> c2 = new HashSet<Integer>();
        c2.add(2); c2.add(5); c2.add(8);
        HashSet<Integer> c3 = new HashSet<Integer>();
        c3.add(3); c3.add(6); c3.add(9);
        HashSet<Integer> d1 = new HashSet<Integer>();
        d1.add(1); d1.add(5); d1.add(9);
        HashSet<Integer> d2 = new HashSet<Integer>();
        d2.add(3); d2.add(5); d2.add(7);

        HashSet<HashSet> set = new HashSet<HashSet>();
        set.add(r1); set.add(r2); set.add(r3);
        set.add(c1); set.add(c2); set.add(c3);
        set.add(d1); set.add(d2);

        for (HashSet c:set)
        {
            if (user_set.containsAll(c))
                return "You Won";
            else if (computer_set.containsAll(c))
                return "You Lost";

        }
        if (user_set.size() + computer_set.size() == 9)
            return "Draw";
        return "";
    }

    static int generate_random()
    {
        int max = 9;
        int min = 1;
        int range = max - min + 1;
        int result = (int) (Math.random() * range) + min;
        return result;
    }
    static void print_board(char [][] game_board)
    {
        for (int r=0; r< game_board.length; r++) {
            for (int c=0; c<game_board[0].length; c++) {
                System.out.print(game_board[r][c]);
            }
            System.out.println();
        }
    }
    static void p_holder(char [][] game_board, int position, String user)
    {
        char sym = 'X';
        if (user.equals("You"))
        {
            sym = 'X';
            user_set.add(position);
        }
        else if (user.equals("CPU"))
        {
            sym = 'O';
            computer_set.add(position);
        }
        switch (position)
        {
            case 1:
                game_board[0][0] = sym;
                break;
            case 2:
                game_board[0][2] = sym;
                break;
            case 3:
                game_board[0][4] = sym;
                break;
            case 4:
                game_board[2][0] = sym;
                break;
            case 5:
                game_board[2][2] = sym;
                break;
            case 6:
                game_board[2][4] = sym;
                break;
            case 7:
                game_board[4][0] = sym;
                break;
            case 8:
                game_board[4][2] = sym;
                break;
            case 9:
                game_board[4][4] = sym;
                break;
            default:
                System.out.println("Invalid Input");
        }
        print_board(game_board);
    }
}