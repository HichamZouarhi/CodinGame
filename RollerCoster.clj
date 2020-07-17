(ns Solution
  (:gen-class))

; Auto-generated code below aims at helping you parse
; the standard input according to the problem statement.

(defn output [msg] (println msg) (flush))
(defn debug [msg] (binding [*out* *err*] (println msg) (flush)))

(defn nextInLine [index start limit] 
  (if (= index (- limit 1))
    start
    (+ index 1)
  )
)

(defn tailRecSum [index indexH startH limitH limit sumRec historyEarnings] 
  (if (= index limit)
    sumRec
    (tailRecSum (+ index 1) (nextInLine indexH startH limitH) startH limitH limit (+ sumRec (get historyEarnings indexH)) historyEarnings)
  )
)

(defn tailRecSumV2 [index indexH startH limitH limit historyEarnings] 
    (loop [sumRec 0 
        i index  
        iH indexH]
        (if (= i limit)
            sumRec
            (recur (+ sumRec (get historyEarnings iH)) (inc i) (nextInLine iH startH limitH))
        )
    )
)


(defn -main [& args]
  (def people (vector))
  (def historyGroups (vector))
  (def historyEarnings (vector))
  (def foundInHistory false)
  (def indexInHistory 0)
  (def upperBoundHistory 0)
  (def lowerBoundHistory 0)
  (def lapCycle 0)

  (def earning 0)
  (def j 0)

  (let [L (read) C (read) N (read)]
    (debug (str "Laps = " C " Capacity = " L))
    (loop [i N]
      (when (> i 0)
        (let [Pi (read)]
          (def people (conj people Pi))
          (recur (dec i)))))

    (debug people)
    (def tolalPeople (reduce + people))
    (if (<= tolalPeople L)
      (do
        (def earning (* C tolalPeople))
      )
      (do
        (loop [i 0]
          (when (and (not foundInHistory) (< i C))
            ;(debug (str "lap " i))
            (def rollerFull false)
            (def peopleInRoller 0)
            (def groupsInRoller 0)
            (def lapEarning 0)
            (def lapGroups "")
            
            (while (and (not rollerFull) (not foundInHistory))
              (do
                (def currentGroup (get people j))
                ;(debug (str "currentGroup = " currentGroup))
                ;(debug (str "index " j))

                
                (def peopleInRollerNow (+ peopleInRoller currentGroup))

                (if (> peopleInRollerNow L)
                  (do
                    (if (= groupsInRoller 0)
                      (do
                        ;(debug "group too large for roller")
                        
                        (def lapGroups (str j))
                        ; check if groups combination exist in history
                        (if (some #{lapGroups} historyGroups)
                          (do
                            (def foundInHistory true)
                            (def indexInHistory (.indexOf historyGroups lapGroups))
                            (def lowerBoundHistory indexInHistory)
                            (def upperBoundHistory (count historyGroups))
                          )
                          (do
                            ; add groups and earnings of the lap to history
                            (def historyGroups (conj historyGroups lapGroups))
                            (def historyEarnings (conj historyEarnings lapEarning))
                          )
                        )
                        
                        (def j (nextInLine j 0 N))
                      )
                      (do
                        ;(debug (str "last group won't fit in roller " peopleInRollerNow))
                      
                        ; check if groups combination exist in history
                        (if (some #{lapGroups} historyGroups)
                          (do
                            (def foundInHistory true)
                            (def indexInHistory (.indexOf historyGroups lapGroups))
                            (def lowerBoundHistory indexInHistory)
                            (def upperBoundHistory (count historyGroups))
                          )
                          (do
                            ; add groups and earnings of the lap to history
                            (def historyGroups (conj historyGroups lapGroups))
                            (def historyEarnings (conj historyEarnings lapEarning))
                          )
                        )
                        (def rollerFull true)
                      )
                    )
                  )
                  (if (= peopleInRollerNow L)
                    (do
                      ;(debug (str "perfect fit " peopleInRollerNow))
                      (def peopleInRoller peopleInRollerNow)
                      (def lapEarning (+ lapEarning currentGroup))
                      (def lapGroups (str j))

                      ; check if groups combination exist in history
                      (if (some #{lapGroups} historyGroups)
                        (do
                          (def foundInHistory true)
                          (def indexInHistory (.indexOf historyGroups lapGroups))
                          (def lowerBoundHistory indexInHistory)
                          (def upperBoundHistory (count historyGroups))
                        )
                        (do
                          ; add groups and earnings of the lap to history
                          (def historyGroups (conj historyGroups lapGroups))
                          (def historyEarnings (conj historyEarnings lapEarning))
                        )
                      )
                      (def j (nextInLine j 0 N))
                      (def rollerFull true)
                    )
                    (if (< peopleInRoller L)
                      (do
                        ;(debug "there is still place in the roller")
                        (def peopleInRoller peopleInRollerNow)
                        (def lapEarning (+ lapEarning currentGroup))
                        (def groupsInRoller (+ groupsInRoller 1))
                        ; add groups and earnings of the lap to history
                        (def lapGroups (str lapGroups " " j))
                        (def j (nextInLine j 0 N))
                      )
                    )
                  )
                )
              )
            )
          
            
            (if foundInHistory
              (do
                (def lapCycle i)
                (debug (str "Cycle starts on lap " i))
                
              )
              (def earning (+ earning lapEarning))
            )

            
            ;(debug (str "earning " earning))
            (recur (inc i))))
          
       
        (if foundInHistory
            ;(def earning (tailRecSum lapCycle indexInHistory lowerBoundHistory upperBoundHistory C 0 historyEarnings))
            (def earning (+ earning (tailRecSumV2 lapCycle indexInHistory lowerBoundHistory upperBoundHistory C historyEarnings)))
        )
      
        )
      )

    )

    (output earning)
    ; (debug "Debug messages...")

    ; Write answer to stdout
  )