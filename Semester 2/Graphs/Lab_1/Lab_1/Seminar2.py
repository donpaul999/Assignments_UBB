import time
import random



class Matrix:
    #A directed graph, represented by adjacency matrix.

    def __init__(self, n):
        self._matrix = []
        for i in range(n):
            self._matrix.append([])
            for j in range(n):
                self._matrix[i].append(False)

    def parseX(self):
        """Returns an iterable containing all the vertices"""
        NumberOfVertices = len(self._matrix)
        return range(NumberOfVertices)

    def ParseOut(self, x):
        """Returns an iterable containing the outbound neighbours of x"""
        list = []
        for i in range(len(self._matrix[x])):
            if self._matrix[x][i]:
                list.append(i)
        return list

    def ParseIn(self, x):
        """Returns an iterable containing the inbound neighbours of x"""
        list = []
        for i in range(len(self._matrix)):
            if self._matrix[i][x]:
                list.append(i)
        return list

    def isEdge(self, x, y):
        """Returns True if there is an edge from x to y, False otherwise"""
        return self._matrix[x][y]

    def addEdge(self, x, y):
        #Adds an edge from x to y.
        self._matrix[x][y] = True


class DictionaryGraph:
    #A directed graph, represented as a map from each vertex to the set of outbound neighbours

    def __init__(self, n):
        self._dict = {}
        for i in range(n):
            self._dict[i] = []

    def parseX(self):
        """Returns an iterable containing all the vertices"""
        return self._dict.keys()

    def ParseOut(self, x):
        """Returns an iterable containing the outbound neighbours of x"""
        return self._dict[x]

    def ParseIn(self, x):
        """Returns an iterable containing the inbound neighbours of x"""
        list = []
        for i in self._dict.keys():
            if x in self._dict[i]:
                list.append(i)
        return list

    def isEdge(self, x, y):
        """Returns True if there is an edge from x to y, False otherwise"""
        return y in self._dict[x]

    def addEdge(self, x, y):
        #Adds an edge from x to y.
        self._dict[x].append(y)


class DoubleDictionaryGraph:
    """A directed graph, represented as two maps,one from each vertex to the set of outbound neighbours,the other from each vertex to the set of inbound neighbours"""
    def __init__(self, n):
        self._dictOut = {}
        self._dictIn = {}
        for i in range(n):
            self._dictOut[i] = []
            self._dictIn[i] = []

    def parseX(self):
        """Returns an iterable containing all the vertices"""
        return self._dictOut.keys()

    def ParseOut(self, x):
        """Returns an iterable containing the outbound neighbours of x"""
        return self._dictOut[x]

    def ParseIn(self, x):
        """Returns an iterable containing the inbound neighbours of x"""
        return self._dictIn[x]

    def isEdge(self, x, y):
        """Returns True if there is an edge from x to y, False otherwise"""
        return y in self._dictOut[x]

    def addEdge(self, x, y):
        """Adds an edge from x to y."""
        self._dictOut[x].append(y)
        self._dictIn[y].append(x)

def initRandomGraph(Constructor, n, m):
    """Constructs and returns a randomly generated graph"""
    g = Constructor(n)
    addedEdges = 0
    while addedEdges < m:
        x = random.randrange(0, n)
        y = random.randrange(0, n)
        if not g.isEdge(x, y):
            g.addEdge(x, y)
            addedEdges += 1
    return g

def printTime(g):
    before = time.time()
    for i in g.parseX():
        for x in g.ParseOut(i):
            pass

    after = time.time()
    print("Parse outbound: %s" % (after - before))

    before = time.time()

    for i in g.parseX():
        for x in g.ParseIn(i):
            pass

    after = time.time()
    print("Parse inbound: %s" % (after-before))

n = 1000
g = initRandomGraph(DoubleDictionaryGraph, n, n * 10)
printTime(g)

'''
Adjacency matrix:

n = 1000, m = n * 10

Parse outbound: 0.1709427833557129
Parse inbound: 0.13453388214111328

n = 10000, m = n * 10

Parse outbound: 13.361797332763672
Parse inbound: 14.270812273025513

n = 100000, m = n * 10

MemoryError

n = 1000000, m = n * 10

MemoryError 

***********************************************************************************

For each vertex we have the collection of outbound neighbours :


n = 1000, m = n * 10

Parse outbound: 0.0
Parse inbound: 0.304002046585083

n = 10000, m = n * 10

Parse outbound: 0.007215738296508789
Parse inbound: 33.8207790851593

n = 100000, m = n * 10

Parse outbound: 0.12343025207519531
Parse inbound: Too much

n = 1000000, m = n * 10

Parse outbound: 1.69517183303833
Parse inbound: Too much

******************************************************************************************

For each vertex we have both the collection of inbound and the collection of outbound neighbours:


n = 1000, m = n * 10

Parse outbound: 0.0
Parse inbound: 0.0

n = 10000, m = n * 10

Parse outbound: 0.013972997665405273
Parse inbound: 0.012604713439941406

n = 100000, m = n * 10

Parse outbound: 0.16308999061584473
Parse inbound: 0.15540146827697754

n = 1000000, m = n * 10

Parse outbound: 1.6387939453125
Parse inbound: 1.5638909339904785

'''