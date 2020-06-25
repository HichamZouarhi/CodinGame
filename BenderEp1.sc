import math._
import scala.util._
import scala.collection.mutable.ArrayBuffer
/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
 
class Bender(var initialL : Int, var initialC : Int, var world_map:World_Map) {
    var l : Int = initialL;
    var c : Int = initialC;
    var breaker_mode = false
    var priorities = Array('S', 'E', 'N', 'W')
    var direction = 'S'
    var arrived = false
    var advance = true
    var obstacle_found = false
    var wm = world_map
    var directions = Map('S' -> "SOUTH", 'E' -> "EAST", 'N' -> "NORTH", 'W' -> "WEST")
    var steps = ArrayBuffer[String]()
    
    def get_next_direction(){
        var i = priorities.indexWhere( _ == direction)
        if (i < 3){
            direction = priorities(i+1)
        }
        else{
            direction = priorities(0)
        }
        
    }
    
    def assess_next_step(){
        var nextL = l
        var nextC = c
        // get next step coordinates
        if (direction == 'S'){
            nextL += 1
        }
        else if (direction == 'E'){
            nextC += 1
        }
        else if (direction == 'N'){
            nextL -= 1
        }
        else if (direction == 'W'){
            nextC -= 1
        }
        
        // check if there is an obstacle ahead
        if (world_map.map(nextL)(nextC) == '#'){
            if(direction != priorities(0) & !obstacle_found){
                direction = priorities(0)
                obstacle_found = true
            }
            else{
                get_next_direction()
            }
            
            
            advance = false
        }
        else if (world_map.map(nextL)(nextC) == 'X'){
            // if bender is on breaker mode
            if (breaker_mode){
                world_map.map(nextL)(nextC) = ' '
                l = nextL
                c = nextC
                advance = true
                obstacle_found = false
                steps = steps :+ directions(direction)
                //println(directions(direction))
            }
            else{
                if(direction != priorities(0) & !obstacle_found){
                    direction = priorities(0)
                    obstacle_found = true
                }
                else{
                    get_next_direction()
                }
                advance = false
            }
        }
        // check if target is ahead
        else if (world_map.map(nextL)(nextC) == '$'){
            arrived = true
            obstacle_found = false
            l = nextL
            c = nextC
            steps = steps :+ directions(direction)
            //println(directions(direction))
        }
        // check if there is a portal ahead
        else if (world_map.map(nextL)(nextC) == 'T'){
            steps = steps :+ directions(direction)
            //println(directions(direction))
            if (nextL == world_map.portal_1L & nextC == world_map.portal_1C){
                l = world_map.portal_2L
                c = world_map.portal_2C
            }
            else if (nextL == world_map.portal_2L & nextC == world_map.portal_2C){
                l = world_map.portal_1L
                c = world_map.portal_1C
            }
        }
                
        else{
            //check if there is a modifier ahead
            steps = steps :+ directions(direction)
            //println(directions(direction))
            if (priorities contains world_map.map(nextL)(nextC)){
                direction = world_map.map(nextL)(nextC)
            }
            // check if there is a beer ahead
            if (world_map.map(nextL)(nextC) == 'B'){
                //Console.err.println("breaker mode toggled")
                breaker_mode = !breaker_mode
            }
            
            // check if there is an inverter ahead
            if (world_map.map(nextL)(nextC) == 'I'){
                priorities = priorities.reverse
            }
            
            l = nextL
            c = nextC
            obstacle_found = false
           
        }
    }
}

class World_Map(var m: Array[Array[Char]], var p_exists:Boolean, var p_1L:Int, var p_1C:Int, var p_2L:Int, var p_2C:Int){
    var map = m
    var portal_exists = p_exists
    var portal_1L = p_1L
    var portal_1C = p_1C
    var portal_2L = p_2L
    var portal_2C = p_2C
}

object Solution extends App {

    val Array(l, c) = for(i <- readLine split " ") yield i.toInt
    val map =  Array.ofDim[Char](l, c)
    var world_map = new World_Map(map, false, 0, 0, 0, 0)
    var startL = 0
    var startC = 0
    var portal_exists = false
    var portal_1L = 0
    var portal_1C = 0
    var portal_2L = 0
    var portal_2C = 0
    for(i <- 0 until l) {
        val row = readLine
        for (j <- 0 until c){
            world_map.map(i)(j) = row.charAt(j)
            if (world_map.map(i)(j) == '@'){
                startL = i
                startC = j
            }
            
            if (world_map.map(i)(j) == 'T'){
                if (!world_map.portal_exists){
                    world_map.portal_exists = true
                    world_map.portal_1L = i
                    world_map.portal_1C = j
                }
                else{
                    world_map.portal_2L = i
                    world_map.portal_2C = j
                }
            }
        }
        Console.err.println(row)
    }
    
    
    
    
    var bender = new Bender(startL, startC, world_map)
    var i = 0
    var size = l * c
    while (! bender.arrived & i < size){
        bender.assess_next_step()
        i += 1
    }
    
    if (bender.arrived){
        for(i <- 0 until bender.steps.length){
            println(bender.steps(i))
        }
    }
    else{
        println("LOOP")
    }
    
    // Write an action using println
    // To debug: Console.err.println("Debug messages...")
    
    //println("answer")
}