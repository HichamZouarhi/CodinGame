/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

var inputs = readline().split(' ');
const N = parseInt(inputs[0]); // the total number of nodes in the level, including the gateways
const L = parseInt(inputs[1]); // the number of links
const E = parseInt(inputs[2]); // the number of exit gateways
var adj = [];
var destinations = [];
for (let i = 0; i < L; i++) {
    var inputs = readline().split(' ');
    const N1 = parseInt(inputs[0]); // N1 and N2 defines a link between these nodes
    const N2 = parseInt(inputs[1]);
    if (adj[N1] === undefined){
        adj[N1] = [N2];
    }
    else{
        adj[N1].push(N2);
    }
    
    if (adj[N2] === undefined){
        adj[N2] = [N1];
    }
    else{
        adj[N2].push(N1);
    }
    
}
for (let i = 0; i < E; i++) {
    const EI = parseInt(readline()); // the index of a gateway node
    destinations.push(EI);
}


function BFS(adj, src, dest){
    var queue = [];
    var visited = [];
    var pred = [];
    var dist = [];
    
    for (let i = 0; i < N; i++){
        visited.push(false);
        dist.push(Infinity);
        pred.push(-1);
    }
    
    visited[src] = true;
    dist[src] = 0;
    queue.push(src);
    
    while (queue.length > 0){
        var u = queue[0];
        queue.shift();
        for(let i = 0; i < adj[u].length; i++){
            if (visited[adj[u][i]] === false){
                visited[adj[u][i]] = true;
                dist[adj[u][i]] = dist[u] + 1;
                pred[adj[u][i]] = u;
                queue.push(adj[u][i]);
                
                if (adj[u][i] == dest){
                    // var path = [];
                    // var crawl = dest;
                    // while(pred[crawl] != -1){
                    //     path.push(pred[crawl]);
                    //     crawl = pred[crawl];
                    // }
                    // console.error(path);
                    return [u, dist[dest]];
                }
            }
        }
    }
    return false;
}

// game loop
while (true) {
    const SI = parseInt(readline()); // The index of the node on which the Skynet agent is positioned this turn
    var closest = BFS(adj, SI, destinations[0]);
    var min_dist = closest[1];
    var previous = closest[0];
    var destination = destinations[0];
    
    
    for (let i = 1; i < E; i++){
        var path = BFS(adj, SI, destinations[i]);
        var distance = path[1];
        if (distance < min_dist){
            min_dist = distance;
            previous = path[0];
            destination = destinations[i];
        }
    }
    
    console.log(previous + ' ' + destination);
    adj[previous].splice(adj[previous].indexOf(destination), 1);
    adj[destination].splice(adj[destination].indexOf(previous), 1);
}
