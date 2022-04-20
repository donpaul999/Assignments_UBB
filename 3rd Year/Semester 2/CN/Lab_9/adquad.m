function I = adquad(f, a, b, er)
  I1 = simpson(f, a, b, 1);
  I2 = simpson(f, a, b, 2);
  if abs(I1 - I2) < 15 * er
    I = I2;
    return
  else
    I = adquad(f, a, (a+b) / 2, er/2) + adquad(f, (a+b)/2, b, er/2);
  endif
endfunction