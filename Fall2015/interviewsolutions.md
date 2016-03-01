Solutions (Pseudocode): 
```
1. void FizzBuzz(int n){ 
     for(int i = 0; i < n; i++){ 
     if(i % 3 == 0) 
       print "Fizz" 
     if(i % 5 == 0) 
       print "Buzz" 
     print "\n" 
} 

2: Returns true when n is a power of two 
3: C++ Solution int HasCycle(Node* head)
{
    Node *n1 = head;
    Node *n2 = head;
//make sure neither are null, or we don't have a cycle
    while(n1 && n2){
//2 iterators for the list
      n1 = n1->next;
      n2 = n2->next;
      if(!n2)
        return 0;
      else
        n2 = n2->next; //n2 goes "twice as fast" as n1
     //this is how we know there is a cycle
      if(n1 == n2)
        return 1;
    } 
    return 0;
} 

4: C++ Solution: 

void Inorder(node *root) { 
    if(!root) { return; } 
    Inorder(root->left); 
    std::cout << root->data << " "; 
    Inorder(root->right); 
} 


5: Java Solution: 
Node MergeLists(Node list1, Node list2) { 
  if (list1 == null) return list2; 
  if (list2 == null) return list1; 
  if (list1.data < list2.data) { 
    list1.next = MergeLists(list1.next, list2); 
    return list1; 
  } else { 
    list2.next = MergeLists(list2.next, list1);
    return list2;
  } } 

6: C++ Solution 
void ReversePrint(Node *head)
{
    if(head->next)
      ReversePrint(head->next);
    std::cout << head->data << std::endl;
} 

7: Python solution def find_intersection(values): 
    all_uniques = [] 
    for value in values: 
      all_uniques.append(set([l for l in value]))
    return len(reduce(lambda x, y: x & y all_uniques))


8: Solution TBD
```
