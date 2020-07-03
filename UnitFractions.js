/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

const n = parseInt(readline());

function getDivisors(n){
    let divisors = [];
    for (let i = 1 ; i <= Math.sqrt(n) ; i++) 
    { 
        if (n % i == 0) 
        {   
            if ( i >= n / i){
                divisors.push([i, n / i]);
            }
            else {
                divisors.push([n / i, i]);
            }
            
        } 
    } 
    return divisors;
}

var pairs = getDivisors(n * n);
for ( let i = 0 ; i < pairs.length ; i++){
    pairs[i][0] += n;
    pairs[i][1] += n;
    console.log("1/" + n + " = " + "1/" + pairs[i][0] + " + 1/" + pairs[i][1]);
}

console.error(pairs);
