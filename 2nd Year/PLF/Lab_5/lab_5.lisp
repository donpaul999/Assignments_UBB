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
 ;insert(L, A)
 ;null, if l is empty
 ;Li + A + insert(Li+1...Ln, A), if i % 2 == 0
 ;Li + insert(Li+1...Ln, A), otherwise

 (defun insert (l a)
    (insert_aux l a 1)
 )

 (defun insert_aux (l a p)
    (cond
        ((null l) nil)
        ((equal (mod p 2) 0) (cons (car l) (cons a (insert_aux (cdr l) a (+ p 1)))))
        (T (cons (car l) (insert_aux (cdr l) a (+ p 1))))
    )
 )


 (print (insert '(1 2 3 4 5 6 7 8) 100))

 ;b)
 ;get_atoms(l)
 ;[], l is empty
 ;get_atoms(li+1...ln) + get_atoms(li), if listp(li) == true
 ;get_atoms(li+1...ln) + li, otherwise

 (defun get_atoms(l)
    (cond
       ((null l) nil)
       ((listp (car l)) (append (get_atoms (cdr l)) (get_atoms(car l))))
       (T (append (get_atoms (cdr l)) (list (car l))))
    )
 )



  (print (get_atoms '(1 2 3 4 5 (A B C) 6 7 8)))

 ;c)
 ;gcd_list(l)
 ;li, if i == n and listp(li) == false
 ;gcd_list(li), if i == n and listp(li) == true
 ;gcd(gcd_list(li), gcd_list(li+1...ln)) , if listp(li) == true
 ;gcd(li, gcd_list(li+1...ln)), if numberp(li) == true
 ;gcd_list(li+1...ln), otherwise

 (defun gcd_list(l)
    (cond
        ((and (equal (length l) 1) (not (listp (car l)))) (car l))
        ((and (equal (length l) 1) (listp (car l))) (gcd_list (car l)))
        ((listp (car l)) (gcd (gcd_list (car l)) (gcd_list(cdr l))))
        ((numberp (car l)) (gcd (car l) (gcd_list (car l))))
        (T (gcd_list (cdr l)))
    )
 )

 (defun gcd(a b)
    (cond
        ((and (not (numberp a)) (not (numberp b))) nil)
        ((not (numberp a)) b)
        ((not (numberp b)) a)
        ((equal b 0) a)
        (T (gcd b (mod a b)))
    )
 )

 (print (gcd_list '(24 ( 16 (12 A B)) 72)))

 ;d)
 