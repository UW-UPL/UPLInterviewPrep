def takeWhile(pred, lst):
    res = []
    for elem in lst:
        if pred(elem):
            res.append(elem)
        else:
            break
    return res

def isAbbrRec(orig, test):
    if not orig and not test:
        return True
    elif (not orig) != (not test):
        return False
    elif orig[0] == test[0]:
        return isAbbrRec(orig[1:], test[1:])
    elif orig[0] != test[0] and test[0].isdigit():
        numeric_prefix = takeWhile(lambda c: c.isdigit(), test)
        prefix_len = len(numeric_prefix)
        num = int(''.join(numeric_prefix))
        return isAbbrRec(orig[num:], test[prefix_len:])
    else:
        return False

def isAbbr(orig, test):
  return isAbbrRec(orig, test)

if __name__ == '__main__':
  test_data = [
    ('LOCALIZATION', 'L10N'), # True
    ('LOCALIZATION', '6Z4N'), # True
    ('LOCALIZATION', 'L9N' ), # False
    ('LOCALIZATION', 'L10Q'), # False
    ('LOCALIZATION', '12'  ), # True
    ('LOCALIZATION', '11'  )  # False
  ]

  for (orig, test) in test_data:
    print(isAbbr(orig, test))
