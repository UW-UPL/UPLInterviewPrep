def look(num):
    s = str(num)
    l = len(s)
    res = ''
    rpt = 0
    chr = s[0]
    i = 0
    
    for i in range(l):
        if s[i] == chr:
            rpt += 1
        else:
            res += str(rpt) + chr
            rpt = 1
            chr = s[i]
    
    if rpt >= 1:
        res += str(rpt) + chr
    
    return int(res)
    
def look_and_say(n):
  num = 1 # the initial number in the sequence
  ctr = 1 # goes up to n
  while ctr <= n:
      print(num) # i.e. say
      num = look(num)
      ctr += 1

look_and_say(7)
