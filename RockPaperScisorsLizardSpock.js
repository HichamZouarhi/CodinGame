/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
function dumpResult(player1, player2, winner, loser){
    console.error(player1[0] + " " + player1[1] + " \\");
    console.error("\t " + winner);
    console.error(player2[0] + " " + player2[1] + " /");
}

var beats = {"R": ["C", "L"], 
            "P": ["R", "S"], 
            "C": ["P", "L"], 
            "L": ["S", "P"], 
            "S": ["R", "C"]};
var players = [];
var wins = {};
const N = parseInt(readline());
for (let i = 0; i < N; i++) {
    var inputs = readline().split(' ');
    const NUMPLAYER = parseInt(inputs[0]);
    const SIGNPLAYER = inputs[1];
    console.error(NUMPLAYER + " " + SIGNPLAYER);
    players.push([NUMPLAYER, SIGNPLAYER]);
    wins[NUMPLAYER] = [];
}
console.error("Game begins --------------------");
while (players.length > 1){
    let losersIndices = [];
    for (let i = 0; i < players.length ; i += 2){
        
        let indexToRemove;
        let winner;
        let loser;
        if (players[i][1] == players[i + 1][1]){
            indexToRemove = players[i][0] > players[i + 1][0] ? i : i + 1;
            winner = players[i][0] > players[i + 1][0] ? players[i + 1][0] : players[i][0];
            loser = players[i][0] > players[i + 1][0] ? players[i][0] : players[i + 1][0];
        }
        else if (beats[players[i][1]].includes(players[i + 1][1])){
            indexToRemove = i + 1;
            winner = players[i][0];
            loser = players[i + 1][0];
        }
        else{
            indexToRemove = i;
            winner = players[i + 1][0];
            loser = players[i][0];
        }
        dumpResult(players[i], players[i+1], winner, loser);
        wins[winner].push(loser);
        losersIndices.push(indexToRemove);
    }
    console.error("list of losers : " + losersIndices);
    for(let i = losersIndices.length - 1 ; i >= 0 ; i--){
        players.splice(losersIndices[i], 1);
    }
    
    console.error("End of round --------------------");
}
// Write an answer using console.log()
// To debug: console.error('Debug messages...');

console.log(players[0][0]);
console.log(wins[players[0][0]].toString().split(",").join(" "));
