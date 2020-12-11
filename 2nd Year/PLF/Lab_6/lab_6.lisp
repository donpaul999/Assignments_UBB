;5. Return the level (depth) of a node in a tree of type (1). The level of the root element is 0.

;get_level(a, b)
;b, a = -1
;a, otherwise

(defun get_level(a b)
    (if (equal a -1)
        b
        a
    )
)

;increment_level(level, r1..rn)
;[-1, r1..rn], level = -1
;[level + 1, r1..rn], otherwise


(defun increment_level(level rest)
    (if (equal level -1)
        (list -1 rest)
        (list (+ level 1) rest)
    )
)

;level_aux(l1..ln, e)
;[-1, []], n = 0
;[0, []], l1 = e
;[-1, l3..ln], l2 = 0
;a1,a21,a22..a2n <- level_aux(l3..ln, e), increment_level(a1, a2) ,l2 = 1
;al1,al21,al22..al2n  <- level_aux(l3..ln, e);
;ar1,ar21,ar22..ar2n <-level_aux(al21..al2n, e), increment_level(get_level(al1, ar1), ar21..ar2n), otherwise


(defun level_aux(l e)
    (cond
        ((null l) (list -1 '()))
        ((equal (car l) e) (list 0 '()))
        ((equal (cadr l) 0) (list -1 (cddr l)))
        ((equal (cadr l) 1) (let ((ans (level_aux (cddr l) e)))
            (increment_level (car ans) (cadr ans))))
        (T (let ((ans_left (level_aux (cddr l) e)))
            (let ((ans_right (level_aux (cadr ans_left) e)))
               (increment_level (get_level (car ans_left) (car ans_right)) (cadr ans_right)))))
    )
)

(defun level(l e)
    (if l
        (car (level_aux l e))
        -1
    )
)

(print (level '(A 2 B 0 C 2 D 0 E 0) 'E))
(print (level '(A 2 B 2 D 0 E 1 H 0 C 2 F 1 I 0 G 0) 'I))
