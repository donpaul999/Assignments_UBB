;12. Write a function that substitutes an element through another one at all levels of a given list.
;(load "lab_7.lisp")
;substitute_elem(l, a, e)
;e, l = a
;substitute_elem(l1, a, e) U substitute_elem(l2, a, e) U ... U substitute_elem(ln, a, e), l is a list, l is l1..ln
;l, otherwise


(defun substitute_elem (l a e)
    (cond
        ((equal l a) e)
        ((listp l) (mapcar #'(lambda (w)(substitute_elem w a e)) l))
        (T l)
    )
)

;(print (substitute_elem '(1 2 (1 100 (1 (1 (1 (1 (1 (1))))) 4)) 500 1 7) 1 5))

;remove_elements(l)
;NIL, l % 3 == 0
;remove_elements(l1) U ... U remove_elements(ln), l is a list, l is l1..ln)
;l, otherwise

(defun remove_elements (l)
    (cond
        ((and (numberp l) (equal (mod l 3) 0)) '())
        ((listp l) (mapcar #'(lambda (w)(remove_elements w)) l))
        (T l)
    )
)

;(print (remove_elements '(1 (2 A (3 A)) (6))))



;10
(defun product_atoms (l)
    (cond
        ((null l) 1)
        ((numberp (car l)) (* (car l) (product_atoms (cdr l))))
        (T (product_atoms (cdr l)))
    )
)

;(print (product_atoms '(1 2 4 (100 (300) 200) 5 6)))


(defun replaceFirstElem (l e a)
    (cond
        ((null l) nil)
        ((= (car l) e) (cons a (cdr l)))
        (T (cons (car l) (replaceFirstElem (cdr l) e a)))
    )
)
;(print (replaceFirstElem '(1 2 3 2 2 2 0) 2 5))


(defun counter(l a)
    (cond
        ((null l) 0)
        ((equal (car l) a) (+ 1 (counter (cdr l) a)))
        (T (counter (cdr l) a))
    )
)

(defun _remove(l a)
    (cond
        ((null l) nil)
        ((equal (car l) a) (_remove (cdr l) a))
        (T (cons (car l) (_remove (cdr l) a)))
    )
)

(defun solve(l)
    (cond
        ((null l) nil)
        (T (cons (list (car l) (counter l (car l))) (solve (_remove (cdr l) (car l)))))
    )
)

;(print (solve '(A B A B A C A)))

(defun _replace(l a e)
    (cond
        ((null l) nil)
        ((equal l a) e)
        ((listp l)(mapcar #'(lambda (w)(_replace w a e)) l))
        (T l)
    )
)

(print (_replace '(A (B (C))(D)(E(F))) 'B 'G))









