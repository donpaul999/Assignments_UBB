(defun test() (+ 1 2 3))

(defun adaug(e l)
    (cond
        ((null l)(cons e nil))
        (t(cons(car l)(adaug e (cdr l))))
    )
 )

 ; 3.
 ; a) Write a function that inserts in a linear list a given atom A after the 2nd, 4th, 6th, ... element.
 ; b) Write a function to get from a given list the list of all atoms, on any
 ;  level, but reverse order. Example:
 ;  (((A B) C) (D E)) ==> (E D C B A)
 ; c) Write a function that returns the greatest common divisor of all numbers in a nonlinear list.
 ; d) Write a function that determines the number of occurrences of a given atom in a nonlinear list.

 ;a)
 ;insert(L, A, i)
 ;[], if l is empty
 ;L1 + A + insert(L2...Ln, A, i + 1), if i % 2 == 0
 ;L1 + insert(L2...Ln, A, i + 1), otherwise

(defun insert_aux (l a p)
    (cond
        ((null l) nil)
        ((equal (mod p 2) 0) (cons (car l) (cons a (insert_aux (cdr l) a (+ p 1)))))
        (T (cons (car l) (insert_aux (cdr l) a (+ p 1))))
    )
 )

 (defun insert (l a)
    (insert_aux l a 1)
 )



 (print (insert '(1 2 3 4 5 6 7 8) 100))

 ;b)
 ;get_atoms(l1...ln)
 ;[], l is empty
 ;get_atoms(l2...ln) + get_atoms(l1), l1 is a list
 ;get_atoms(l2...ln) + l1, otherwise

 (defun get_atoms(l)
    (cond
       ((null l) nil)
       ((listp (car l)) (append (get_atoms (cdr l)) (get_atoms(car l))))
       (T (append (get_atoms (cdr l)) (list (car l))))
    )
 )



  (print (get_atoms '(1 2 3 4 5 (A B C) 6 7 8)))

 ;c)
 ;gcd_list(l1...ln)
 ;l, if size of l == 1
 ;gcd(gcd_list(l1), gcd_list(l2...ln)), l1 is a list
 ;gcd(l1, gcd_list(l2...ln)), otherwise
 ;
 ;_gcd(a b)
 ;

  (defun _gcd(a b)
     (cond
         ((and (not (numberp a)) (not (numberp b))) nil)
         ((not (numberp a)) b)
         ((not (numberp b)) a)
         ((equal b 0) a)
         (T (_gcd b (mod a b)))
     )
  )

  (defun gcd_list(l)
     (cond
        ((and (atom (car l)) (null (cdr l))) (car l))
        ((listp (car l)) (_gcd (gcd_list (car l)) (gcd_list (cdr l))))
        (T (_gcd (car l) (gcd_list (cdr l))))
     )
  )


  (print (gcd_list '(24 ( 16 (12 A B)) 72)))

 ;d)
 ;occurences(l, e)
 ;0, if l is empty
 ;1 + occurences(l2...ln), l1 is atom and l1 = e
 ;occurences(l2...ln), l1 is atom and l1 != e
 ;occurences(l1) + occurences(l2...ln), l1 is not atom


 (defun occurences(l e)
    (cond
        ((null l) 0)
        ((and (atom (car l)) (equal (car l) e)) (+ 1 (occurences (cdr l) e)))
        ((atom (car l)) (occurences (cdr l) e))
        ((not (atom (car l))) (+ (occurences (car l) e) (occurences (cdr l) e)))
        (T (occurences (car l) e))
    )
 )

 (print (occurences '(1 (6 (3 4 3) (5 3)) 3 3) 3))

