def pairy_time(k, A):
    
    # Since we only want to find multiplies
    # (i.e. "n*k for some integer n")
    # let's mod the entire list
    
    A_mod = [(a % k) for a in A]
    
    # We will keep track of indices in this dictionary
    # Think of it as a map for a value mod k => index in A_mod
    
    dct = {}
    
    # I initially used an array, but the `k - v` trick causes
    # a bounds error. You could also argue that a dictionary
    # is more efficient/scalable for large but "spare" input lists.
    
    for i, v in enumerate(A_mod):
        if dct.get(k - v) is not None:
            j = dct.get(k - v)
            return [i, j]
        else:
            dct[v] = i
            
            # Special check required for zero since
            # k - 0 = k, which will never appear because of the mod
            
            if v == 0:
                dct[k] = i
    
    # Unsuccessful search
    
    return [None, None]


### TESTS ###
print(str(pairy_time(3, [-2, 2])))
print(str(pairy_time(8, [1, -2, -9, 1, 1, 1])))
print(str(pairy_time(7, [0, 3, 1, 1, 0])))
print(str(pairy_time(4, [])))
print(str(pairy_time(3, [1, 1])))
