import csv
import math
import operator
from random import sample
import matplotlib.pyplot as plt

EP = 0.001

class Point:
    def __init__(self, val_1, val_2, labelp):
        self.val1 = float(val_1)
        self.val2 = float(val_2)
        self.label = labelp
        self.cluster = None

    def __repr__(self):
        return self.label + ',' + str(self.val1) + ',' + str(self.val2)

    def __eq__(self, p):
        if abs(p.val1 - self.val1) <= EP and abs(p.val2 - self.val2) <= EP:
            return 1
        return 0

    def closestCluster(self, clusters):
        min = -1
        for c in clusters:
            if min == -1:
                min = c
                continue
            if math.dist((c.mean_val1, c.mean_val2), (self.val1, self.val2)) < math.dist((min.mean_val1, min.mean_val2), (self.val1, self.val2)):
                min = c
        return min

class Cluster:
    def __init__(self, labelp):
        self.label = labelp
        self.points = []
        self.mean_val1 = 0
        self.mean_val2 = 0


    def addPoint(self, point):
        self.points.append(point)

        if point.cluster:
            point.cluster.points.remove(point)

        point.cluster = self
        return self.updateMeans()

    def updateMeans(self):
        old_val1 = self.mean_val1
        old_val2 = self.mean_val2

        sum_val1 = 0
        sum_val2 = 0
        for i in self.points:
            sum_val1 += i.val1
            sum_val2 += i.val2
        self.mean_val1 = sum_val1 / len(self.points)
        self.mean_val2 = sum_val2 / len(self.points)

        if abs(self.mean_val1 - old_val1) <= EP and abs(self.mean_val2 - old_val2) <= EP:
            return False
        return True


    def updateLabel(self):
        freq = {'A': 0, 'B': 0, 'C': 0, 'D': 0}
        for i in self.points:
            freq[i.label] += 1

        self.label = max(freq.items(), key=operator.itemgetter(1))[0]

    def getStats(self, points):
        TP = FP =  TN = FN = 0

        for p in self.points:
            if p.label == self.label:
                TP += 1
            else:
                FP += 1

        for p in points:
            if p not in self.points:
                if p.cluster.label != self.label:
                    TN  += 1
                else:
                    FN += 1

        acc = (TP + TN) / (TP + TN + FP + FN)
        prec = TP / (TP + FP)
        rec = TP / (TP + FN)
        score = 2 / ((1 / rec) / (1 / prec))

        return acc,prec,rec,score


def readPoints():
    points = []
    with open('dataset.csv') as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                line_count += 1
            else:
                point = Point(row[1], row[2], row[0])
                points.append(point)
                line_count += 1
    return points

def printPoints(points):
    f = open("output_points.csv", 'w')
    f.write('cluster_label,label,val1,val2\n')
    for p in points:
        f.write(p.cluster.label + ',' + p.label + ',' + str(p.val1) + ',' + str(p.val2))
    f.close()


def printClusters(clusters):
    f = open("output_clusters.csv", 'w')
    f.write('label,mean_val1,mean_val2\n')
    for c in clusters:
        f.write(c.label + ',' + str(c.mean_val1) + ',' + str(c.mean_val2))
    f.close()

def printStats(clusters, points):
    for c in clusters:
        acc, prec, rec, score = c.getStats(points)
        print("******\n")
        print(c.label)
        print("Accuracy " + str(acc))
        print("Precision " + str(prec))
        print("Recall/Rappel " + str(rec))
        print("Score " + str(score))


def main():
    clusters = []
    points = readPoints()

    clusters.append(Cluster('A'))
    clusters.append(Cluster('B'))
    clusters.append(Cluster('C'))
    clusters.append(Cluster('D'))

    random_points = sample(points, 4)
    for i in range(0, 4):
        clusters[i].mean_val1 = random_points[i].val1
        clusters[i].mean_val2 = random_points[i].val2

    ok = True
    while ok:
        ok = False
        for p in points:
            optimal_cluster = p.closestCluster(clusters)

            if optimal_cluster.addPoint(p):
                ok = True

    for i in clusters:
        i.updateLabel()

    printPoints(points)
    printClusters(clusters)
    printStats(clusters, points)
    plot(clusters)


def plot(clusters):
    for cluster in clusters:
        x = [point.val1 for point in cluster.points]
        y = [point.val2 for point in cluster.points]

        symbol = ''
        if cluster.label == 'A':
            symbol = 'ro'
        if cluster.label == 'B':
            symbol = 'bo'
        if cluster.label == 'C':
            symbol = 'go'
        if cluster.label == 'D':
            symbol = 'yo'

        plt.plot(x, y, symbol, label=cluster.label)

    plt.legend()
    plt.show()

main()
