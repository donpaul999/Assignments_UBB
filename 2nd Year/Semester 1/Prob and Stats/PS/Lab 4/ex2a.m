pkg load statistics;
p = input("Give p:")
n = input("Give n:")
success = 0;
failure = 0;
for i = 1:n
  x = rand();
  if (x < p)
    success = success + 1;
  else  
    failure = failure + 1;
  endif
endfor
approximated_p = success / n
approximated_1minusp = failure / n