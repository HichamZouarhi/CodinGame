/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

const speed = parseInt(readline());
const lightCount = parseInt(readline());

var distance_sum = 0;
var durations = [];
var distances = []
for (let i = 0; i < lightCount; i++) {
    var inputs = readline().split(' ');
    const distance = parseInt(inputs[0]);
    const duration = parseInt(inputs[1]);
    // console.error(distance);
    // console.error(duration);
    distances.push(distance);
    durations.push(duration);
}

console.error("speed limit : " + speed);

for(let i = lightCount - 1; i > 0; i--){
    distances[i] -= distances[i-1];
}
console.error("durations : " + durations);
console.error("distances : " + distances);
var isSpeedOK = function(durations, distances, currentSpeed){
    var timelapse = 0;
    for (let i = 0; i < lightCount; i++){
        timelapse += distances[i] * 3.6 / currentSpeed;
        if (Math.abs(currentSpeed - 67) <= 1){
            console.error("on light : " + i + 
                    " with duration : " + durations[i] + 
                    " and distance : " + distances[i]);
            console.error("current speed : " + currentSpeed + " timelapse : " + timelapse);
        }
        if (timelapse > durations[i]){
        if (parseInt((timelapse / durations[i]) % 2) !== 0){
            if (Math.abs(currentSpeed - 67) <= 1)
                console.error((timelapse / durations[i]));
            return false;
        }
        }
    }
    return true;
}

var speedFound = false;
var currentSpeed = speed;
while(!speedFound){
    speedFound = isSpeedOK(durations, distances, currentSpeed);
    if(!speedFound){
        currentSpeed--;
    }
}

console.log(currentSpeed);
