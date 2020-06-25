import 'dart:io';
import 'dart:math';

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
void main() {
    List inputs;
    inputs = stdin.readLineSync().split(' ');
    int W = int.parse(inputs[0]); // width of the building.
    int H = int.parse(inputs[1]); // height of the building.
    int N = int.parse(stdin.readLineSync()); // maximum number of turns before game over.
    inputs = stdin.readLineSync().split(' ');
    int X0 = int.parse(inputs[0]);
    int Y0 = int.parse(inputs[1]);

    int stepH = (H / 2).round();
    int stepW = (W / 2).round();
    // game loop
    while (true) {
        String bombDir = stdin.readLineSync(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)

        // Write an action using print()
        // To debug: stderr.writeln('Debug messages...');
        stderr.writeln(bombDir);
        if (bombDir == 'U'){
            Y0 -= ((Y0 - stepH) < 0) ? 1 :stepH;
            stepH = (stepH / 2).round();
        }
        else if (bombDir == 'UR'){
            //stderr.writeln('stepW' + stepW.toString());
            X0 += ((X0 + stepW) >= W) ? 1 :stepW;
            Y0 -= ((Y0 - stepH) < 0) ? 1 :stepH;
            stepH = (stepH / 2).round();
            stepW = (stepW / 2).round();
        }
        else if (bombDir == 'UL'){
            X0 -= ((X0 - stepW) < 0) ? 1 :stepW;
            Y0 -= ((Y0 - stepH) < 0) ? 1 :stepH;
            stepH = (stepH / 2).round();
            stepW = (stepW / 2).round();
        }
        else if (bombDir == 'R'){
            X0 += ((X0 + stepW) >= W) ? 1 :stepW;
            stepW = (stepW / 2).round();
        }
        else if (bombDir == 'L'){
            X0 -= ((X0 - stepW) < 0) ? 1 :stepW;
            stepW = (stepW / 2).round();
        }
        else if (bombDir == 'DR'){
            X0 += ((X0 + stepW) >= W) ? 1 :stepW;
            Y0 += ((Y0 + stepH) >= H) ? 1 :stepH;
            stepH = (stepH / 2).round();
            stepW = (stepW / 2).round();
        }
        else if (bombDir == 'DL'){
            X0 -= ((X0 - stepW) < 0) ? 1 :stepW;
            Y0 += ((Y0 + stepH) >= H) ? 1 :stepH;
            stepW = (stepW / 2).round();
            stepH = (stepH / 2).round();
        }
        else if (bombDir == 'D'){
            Y0 += ((Y0 + stepH) >= H) ? 1 :stepH;
            stepH = (stepH /2).round();
        }
        stepH = stepH.round();
        stepW = stepW.round();
        // the location of the next window Batman should jump to.
        print(X0.toString() + ' ' + Y0.toString());
    }
}