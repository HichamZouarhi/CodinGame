/**
 * Don't let the machines win. You are humanity's last hope...
 **/

const width = parseInt(readline()); // the number of cells on the X axis
const height = parseInt(readline()); // the number of cells on the Y axis
var targets = [];
var battlefield = [];
for (let i = 0; i < height; i++) {
    const line = readline(); // width characters, each either 0 or .
    console.error(line);
    battlefield.push(line);
    for( let j = 0; j < width; j++){
        if (line[j] == '0'){
            targets.push([i, j])
        }
    }
}
for(let i = 0; i < targets.length; i++){
    var target = targets[i][1] + ' ' + targets[i][0];
    
    var rightTargetFound = false;
    for (let x = targets[i][1] + 1 ; x < width; x++){
        if (battlefield[targets[i][0]][x] == "0"){
            target += " " + x + " " + targets[i][0];
            rightTargetFound = true;
            break;
        }
    }
    
    if(!rightTargetFound){
        target += " -1 -1";
    }
    
    var bottomTargetFound = false;
    for (let y = targets[i][0] + 1; y < height; y++){
        if (battlefield[y][targets[i][1]] == '0'){
            target += " " + targets[i][1] + " " + y;
            bottomTargetFound = true;
            break;
        }
    }
    
    if (!bottomTargetFound){
        target += " -1 -1";
    }
    
    console.error(target);
    console.log(target);
}


