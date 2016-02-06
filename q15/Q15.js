'use strict';

/* THE NODE CLASS */
function TreeNode(data, left, right) {
  this.data = data;
  this.left =   left   || null;
  this.right =  right  || null;
}

TreeNode.prototype.add = function(newNode, cF) {
  var compVal = cF(this.data, newNode.data);

  if (compVal === 0) {
    // do nothing
    return;
  }
  else if (compVal < 0) {
    if (!this.left) {
      this.left = newNode;
    } else {
      this.left.add(newNode, cF);
    }
  } else {
    if (!this.right) {
      this.right = newNode;
    } else {
      this.right.add(newNode, cF);
    }
  }
};

TreeNode.prototype.contains = function(data, cF) {
  var compVal = cF(this.data, data);

  if (compVal === 0) {
    return true;
  } else if (compVal < 0 && this.left) {
    return this.left.contains(data, cF);
  } else if (compVal > 0 && this.right) {
    return this.right.contains(data, cF);
  } else {
    return false;
  }
};

/* THE SET CLASS */
function TreeSet(compareFunc) {
  this.root = null;
  this.compareFunc = compareFunc || function (a, b) { return a - b };
}

TreeSet.prototype.add = function() {
  for (var dataKey in arguments) {
    var data = arguments[dataKey];
    var newNode = new TreeNode(data);

    if (!this.root) {
      this.root = newNode;
    } else {
      this.root.add(newNode, this.compareFunc);
    }
  }

  return this;
};

TreeSet.prototype.contains = function(data) {
  if (!this.root) {
    return false;
  } else {
    return this.root.contains(data, this.compareFunc);
  }
}

TreeSet.prototype.toList = function() {
  var queue = [];
  var list = [];

  queue.push(this.root);

  while (queue.length) {
    var curr = queue.shift();

    if (curr) {
      queue.push(curr.left, curr.right);
      list.push(curr.data);
    }
  }

  return list.sort(this.compareFunc);
};

/* REQUIRED METHODS */
TreeSet.prototype.union = function(other) {
  var newTree = new TreeSet();
  this.toList().concat(other.toList()).forEach(function(data) {
    newTree.add(data);
  });

  return newTree;
};

TreeSet.prototype.intersection = function(other) {
  var newTree = new TreeSet();

  this.toList().forEach(function(data) {
    if (other.contains(data) === true) {
      newTree.add(data);
    }
  });

  return newTree;
}

TreeSet.prototype.difference = function(other) {
  var newTree = new TreeSet();

  this.toList().forEach(function(data) {
    if (other.contains(data) === false) {
      newTree.add(data);
    }
  });

  return newTree;
};

/* TESTING (i.e. Q16) */
var a = new TreeSet();
a.add(1, 2, 3);

var b = new TreeSet();
b.add(2, 3);

var assert = function(actual, expected) {
  var aS = actual.toString();
  var eS = expected.toString();

  if (aS == eS) {
    console.log('Test passed');
  } else {
    console.log('Expected ' + aS + ' to be ' + eS);
  }
};

assert(a.union(b).toList(), [1, 2, 3]);
assert(a.intersection(b).toList(), [2, 3]);
assert(a.difference(b).toList(), [1]);
assert(a.union(new TreeSet()).toList(), [1, 2, 3]);
assert(b.intersection(new TreeSet()).toList(), []);
assert((new TreeSet()).difference(a).toList(), []);
