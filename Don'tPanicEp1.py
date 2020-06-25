import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

# nb_floors: number of floors
# width: width of the area
# nb_rounds: maximum number of rounds
# exit_floor: floor on which the exit is found
# exit_pos: position of the exit on its floor
# nb_total_clones: number of generated clones
# nb_additional_elevators: ignore (always zero)
# nb_elevators: number of elevators
nb_floors, width, nb_rounds, exit_floor, exit_pos, nb_total_clones, nb_additional_elevators, nb_elevators = [int(i) for i in input().split()]
print("exit floor : " + str(exit_floor) + " exit pos : " + str(exit_pos), file = sys.stderr)
elevators_pos = {}
for i in range(nb_elevators):
    # elevator_floor: floor on which this elevator is found
    # elevator_pos: position of the elevator on its floor
    elevator_floor, elevator_pos = [int(j) for j in input().split()]
    elevators_pos[elevator_floor] = elevator_pos


def is_elevator_ahead(clone_pos, elevator_pos, direction):
    print(direction, file = sys.stderr)
    if direction == 'LEFT':
        next_clone_pos = clone_pos - 1
    elif direction == 'RIGHT':
        next_clone_pos = clone_pos + 1
    return abs(elevator_pos - next_clone_pos) <= abs(elevator_pos - clone_pos) or elevator_pos == clone_pos

# game loop
while True:
    # clone_floor: floor of the leading clone
    # clone_pos: position of the leading clone on its floor
    # direction: direction of the leading clone: LEFT or RIGHT
    clone_floor, clone_pos, direction = input().split() 
    print("clone_floor : " + clone_floor + " clone_pos : " + clone_pos, file=sys.stderr)
    clone_floor = int(clone_floor)
    clone_pos = int(clone_pos)
    if direction == 'NONE':
        print("WAIT")
    else:
        if (clone_pos < width - 1 and clone_pos > 0):
            if nb_elevators > 0:
                try:
                    go = is_elevator_ahead(clone_pos, elevators_pos[clone_floor], direction)
                except KeyError:
                    go = is_elevator_ahead(clone_pos, exit_pos, direction)
                if go:
                   print("WAIT")
                else:
                    print("BLOCK")
            else:
                print("WAIT")
        else:
            print("BLOCK")
    
