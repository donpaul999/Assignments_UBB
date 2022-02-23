function   g = myFirstFunction  (n)
  if (n  == 0)
    g  =  2
  else
    g =  1 + 1 /(1 +  (myFirstFunction  (n-1)))
  endif
endfunction