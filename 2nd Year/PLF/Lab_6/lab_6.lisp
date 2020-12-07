;5. Return the level (depth) of a node in a tree of type (1). The level of the root element is 0.

;level(l, e)
;nil, l is empty
;0, l1 is not a number and l1 == e
;1 + (l2..ln, e), otherwise

(defun level(l e)
    (cond
        ((null l) 0)
        ((equal (car l) e) 0)
        ((and (numberp (car l)) (= (car l) 0)) 0)
        ((and (and (numberp (car l)) (= (car l) 1)) (equal (cadr l) e)) 1)
        ((and (numberp (car l)) (= (car l) 1)) (+ 1 (level (cddr l) e)))
        ((and (and (numberp (car l)) (= (car l) 2)) (or (equal (cadr l) e) (equal (caddr l) e))) 1)
        ((and (numberp (car l)) (= (car l) 2)) (+ 1 (level (cdddr l) e)))
        (T (level (car l) e))
    )
)

;(print (level '(A 2 B 0 C 2 D 0 E 0) E))

(defun parcurg_st(arb nv nm)
    (cond
        ((null arb) nil)
        ((= nv (+ 1 nm)) nil)
        (t (cons (car arb) (cons (cadr arb) (parcurg_st (cddr arb) (+ 1 nv) (+ (cadr arb) nm)))))
    )
 )

(defun stang(arb)
    (parcurg_st (cddr arb) 0 0)
)

