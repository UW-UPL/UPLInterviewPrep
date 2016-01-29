import sys

def prependAbbrev(front, abbr):
    if type(front) is type(abbr[0]):
        return [front + abbr[0]] + abbr[1:]
    else:
        return [front] + abbr

def prefixAll(p, lst):
    return [prependAbbrev(p, l) for l in lst]
    
def findAllAbbrev(s):
    if len(s) == 1:
        return [[s], [1]]
    else:
        rest = findAllAbbrev(s[1:])
        return prefixAll(s[0], rest) + prefixAll(1, rest)

for s in findAllAbbrev(sys.argv[1]):
    print ''.join([str(i) for i in s])
