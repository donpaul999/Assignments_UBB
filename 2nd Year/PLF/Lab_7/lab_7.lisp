;12. Write a function that substitutes an element through another one at all levels of a given list.

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
(print (substitute_elem '(1 2 (1 100 (1 (1 (1 (1 (1 (1))))) 4)) 500 1 7) 1 5))


