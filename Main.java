import java.util.Scanner;
import java.io.IOException;


public class Main {

    static int sizeX, sizeY;
    static int mizeX, mizeY;
    static int m[][];
    static int r = 0, mr = 0;
    static Scanner sc = new Scanner(System.in);
    static int EFlag = 0;
    static int flagW = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        int ar[][];

        while (true) {

            Menu();
            if (flagW == 0) {
                Sizes();
                ar = new int[sizeX][sizeY];
                System.out.println("Введите исходный двумерный массив чисел: ");
                for (int i = 0; i < sizeX; i++) for (int j = 0; j < sizeY; j++) ar[i][j] = sc.nextInt();

            }
            else {
                ar = new int[mizeX][mizeY];
                for (int i = 0; i < mizeX; i++) {
                    for (int j = 0; j < mizeY; j++) {
                        System.out.print(m[i][j] + " ");
                        ar[i][j] = m[i][j];
                    }
                    System.out.println();
                }
            }

            Choice();
            Rewrite(ar);

            if (EFlag == 1) return;

            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            r = 0;
            mr = 0;
            flagW++;
            sizeX = mizeX;
            sizeY = mizeY;
        }
    }

    static int[][] Rotate0(int ar[][]) {
        m = new int[sizeY][sizeX];
        mizeX = sizeX;
        mizeY = sizeY;
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) m[i][j] = ar[i][j];
        }
        return m;
    }

    static int[][] Rotate90(int ar[][]) {
        m = new int[sizeY][sizeX];
        mizeX = sizeY;
        mizeY = sizeX;
        int cc = sizeY - 1;
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                m[j][i] = ar[i][cc];
                cc--;
            }
            cc = sizeY - 1;
        }
        return m;
    }

    static int[][] Rotate180(int ar[][]) {
        m = new int[sizeX][sizeY];
        mizeX = sizeX;
        mizeY = sizeY;
        int cc = sizeY - 1;
        int pp = sizeX - 1;
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                m[i][j] = ar[pp][cc];
                cc--;
            }
            cc = sizeY - 1;
            pp--;
        }
        return m;
    }

    static int[][] Mirr1(int ar[][]) {
        m = new int[sizeX][sizeY];
        int pp = sizeX - 1;
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                m[pp][j] = ar[i][j];
            }
            pp--;
        }
        return m;
    }

    static int[][] Mirr2(int ar[][]) {
        m = new int[sizeX][sizeY];
        int cc = sizeY - 1;
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                m[i][cc] = ar[i][j];
            }
            cc--;
        }
        return m;
    }

    static void Menu() {

        System.out.println("Добро пожаловать!!" + '\n' +
                "Данная программа реализовывает поворот и отражение двумерного массива" + '\n');
        System.out.println("Возможные операции: " + '\n');
        System.out.println("Повернуть на 90 градусов против часовой стрелки - 1" + '\n' +
                "Повернуть на 180 градусов против часовой стрелки - 2" + '\n' +
                "Повернуть на 270 градусов против часовой стрелки - 3");

        System.out.println("Повернуть на 90 градусов по часовой стрелке - 4" + '\n' +
                "Повернуть на 180 градусов по часовой стрелке - 5" + '\n' +
                "Повернуть на 270 градусов по часовой стрелке - 6");
        System.out.println("Отразить вертикально - 7" + '\n' +
                "Отразить горизонтально - 8" + '\n');
        System.out.println("Выйти - 9" + '\n');


    }

    static void Sizes() {

        System.out.println("Введите размер массива(высота, длина): ");
        sizeX = sc.nextInt();
        sizeY = sc.nextInt();

    }

    static void Choice() {

        char ch = '0';
        String s;

        System.out.println("Введите номер операции, которую Вы хотите выполнить: ");
        s = sc.next();
        ch = s.charAt(0);

        switch (ch) {
            case '1':
            case '6':
                r = 90;
                break;
            case '2':
            case '5':
                r = 180;
                break;
            case '3':
            case '4':
                r = 270;
                break;
            case '7':
                mr = 1;
                break;
            case '8':
                mr = 2;
                break;
            case '9':
                EFlag = 1;
        }

    }

    static void Rewrite(int ar[][]) {

        if (r != 0){
            switch (r) {
                case 90:
                    Rotate90(ar);
                    break;
                case 180: {
                    Rotate180(ar);
                    break;
                }
                case 270: {
                    Rotate90(Rotate180(ar));
                    break;
                }
            }
        }
        else if (mr != 0) {
            switch (mr) {
                case 1:
                    Mirr1(ar);
                    break;
                case 2:
                    Mirr2(ar);
                    break;
            }
        }
        else {
            Rotate0(ar);
            System.out.print("Похоже, что вы ввели несуществующую операцию, попробуйте снова" + '\n');
        }
    }

}

