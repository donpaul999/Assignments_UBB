from repository import *
import time

from utils import *

class Controller():
    def __init__(self, repo):
        # args - list of parameters needed in order to create the controller
        self._numberOfSeeds = None
        self._repository = repo
        self._stepsNb = None
        self._populationSize = None
        self._numberOfIterations = None
        self._mutationProbability = None
        self._crossoverProbability = None
        self._statistics = [] #[[average_fitness_1, random_seed_1],...[average_fitness_numberOfIterations_, random_seed__numberOfIterations]]
        self._iteration = 0
        self._last_stats = []

        self._list_of_sensors = []
        self._sensors_paths = []
        self._visibility_matrix = None
        self._pheromone_matrix = None
        self._colonySize = 0
        self._probability_ranges = []

    def setColonySize(self, size):
        self._colonySize = size

    def run_aco(self):
        self._list_of_sensors = self._repository.getSensors()
        self.computeSensorsPaths()
        self.computeVisibilityMatrix()
        self.computePheromoneMatrix()

        self._repository.addColony(self._colonySize, self._stepsNb)

        best_ant = None
        for i in range(self._numberOfIterations):
            if i == 0:
                best_ant = self.acoIteration()
            else:
                ant = self.acoIteration()
                if len(best_ant.path) >= len(ant.path):
                    best_ant = ant

        return best_ant, self._list_of_sensors

    def acoIteration(self):
        colony = self._repository.currentColony()

        for ant in colony.ants:
            visibility_matrix = self._visibility_matrix.copy()
            while ant.energy > 0 and len(ant.visited_sensors) < len(self._list_of_sensors):
                for i in range(len(self._list_of_sensors)):
                    visibility_matrix[i][ant.current_sensor] = 0

                self.updateProbabilityRanges(ant)

                next_sensor_probability = ant.chooseUnvisitedSensor(self._probability_ranges)
                next_sensor = self.getNextSensorFromProbability(ant, next_sensor_probability)

                if next_sensor is None:
                    continue

                path = self._sensors_paths[ant.current_sensor][next_sensor]
                ant.addPath(path)
                ant.energy -= len(path)

                if ant.energy < 0:
                    ant.path = ant.path[0:len(ant.path) - abs(ant.energy)]
                    ant.energy = 0
                elif ant.energy > 0:
                    energy_needed = self._list_of_sensors[next_sensor].getEnergyNeeded()
                    ant.energy -= energy_needed

                    if ant.energy < 0:
                        old_energy = ant.energy + energy_needed
                        ant.squares_visited_by_sensors[next_sensor] = self._list_of_sensors[next_sensor].discovered_squares[old_energy]
                        ant.energy = 0
                    else:
                        ant.squares_visited_by_sensors[next_sensor] = self._list_of_sensors[next_sensor].discovered_squares[energy_needed]

                    ant.current_sensor = next_sensor
                    ant.visited_sensors.append(next_sensor)

        self.updatePheromoneMatrix(colony)
        best_ant = self.getBestAnt()

        colony.reinitializeAnts(self._stepsNb)

        return best_ant

    def getBestAnt(self):
        min_fitness = -1
        min_ant = None
        for ant in self._repository.currentColony().ants:
            if ant.fitness() < min_fitness or min_fitness == -1:
                min_fitness = ant.fitness()
                min_ant = ant

        return min_ant

    def updatePheromoneMatrix(self, colony):
        for i in range(len(self._list_of_sensors)):
            for j in range(len(self._list_of_sensors)):
                self._pheromone_matrix[i][j] = (1 - evap_coef) * self._pheromone_matrix[i][j]

        for ant in colony.ants:
            visited = ant.visited_sensors
            for i in range(len(visited) - 1):
                self._pheromone_matrix[visited[i]][visited[i + 1]] += (1 / len(ant.path))

    def getNextSensorFromProbability(self, ant, next_sensor_probability):
        next_sensor_probability += 1

        current_next_sensor = 0

        for i in range(1, len(self._list_of_sensors)):
            if i not in ant.visited_sensors:
                current_next_sensor += 1
                if current_next_sensor == next_sensor_probability:
                    return i

    def updateProbabilityRanges(self, ant):
        current_sensor = ant.current_sensor
        num = []
        for j in range(1, len(self._list_of_sensors)):
            if j not in ant.visited_sensors:
                num.append(self._pheromone_matrix[current_sensor][j] ** alpha * self._visibility_matrix[current_sensor][j] ** beta)

        den = sum(num)

        prob = []

        for i in num:
            prob.append(i / den)

        self._probability_ranges = []

        for i in range(len(prob)):
            self._probability_ranges.append(sum(prob[i:]))


    def computePheromoneMatrix(self):
        self._pheromone_matrix = np.ones((len(self._list_of_sensors), len(self._list_of_sensors)))

    def computeVisibilityMatrix(self):
        self._visibility_matrix = np.zeros((len(self._list_of_sensors), len(self._list_of_sensors)))
        for i in range(len(self._list_of_sensors)):
            for j in range(len(self._list_of_sensors)):
                if i != j:
                    self._visibility_matrix[i][j] = 1 / len(self._sensors_paths[i][j])

        for i in range(len(self._sensors_paths)):
            self._visibility_matrix[i][0] = 0


    def computeSensorsPaths(self):
        self._sensors_paths = [[] for _ in range(len(self._list_of_sensors))]
        for i in range(len(self._list_of_sensors)):
            for j in range(len(self._list_of_sensors)):
                self._sensors_paths[i].append(self.searchAStar(self._repository.cmap, self._list_of_sensors[i].x, self._list_of_sensors[i].y, self._list_of_sensors[j].x, self._list_of_sensors[j].y))


    def setSeedNb(self, nb):
        self._numberOfSeeds = nb

    def setDronePosition(self, x, y):
        self._repository.setDronePositions(x, y)

    def setSteps(self, steps):
        self._stepsNb = steps

    def setNumberOfIterations(self, iterations):
        self._numberOfIterations = iterations

    def setMutationProbability(self, probability):
        self._mutationProbability = probability

    def setPopulationSize(self, size):
        self._populationSize = size

    def setCrossoverProbability(self, probability):
        self._crossoverProbability = probability

    def iteration(self, args = 0):
        #print("Iteration " + str(self._iteration))

        self._iteration += 1
        # args - list of parameters needed to run one iteration
        # a iteration:
        # selection of the parents
        # create offsprings by crossover of the parents
        # apply some mutations
        # selection of the survivors

        population = self._repository.currentPopulation()
        self._repository.evaluatePopulation(population)
        select = population.selection(population.size() - 2)
        parents = select[:len(select) // 2]
        pairs = len(parents) // 2
        used_pairs = []
        nb_of_pairs = 0
        for i in range(pairs):
            first = parents[randint(0, len(parents) - 1)]
            second = parents[randint(0, len(parents) - 1)]
            if [first, second] not in used_pairs:
                nb_of_pairs += 2
                used_pairs.append([first, second])
                firstCrossed, secondCrossed = first.crossover(second, self._crossoverProbability)
                first.mutate(self._mutationProbability)
                secondCrossed.mutate(self._mutationProbability)
                self._repository.addIndividual(population, firstCrossed)
                self._repository.addIndividual(population, secondCrossed)
        select = population.selection(population.size() - nb_of_pairs)
        population.setIndividuals(select)



        
    def run(self, args = 0):
        # args - list of parameters needed in order to run the algorithm
        # until stop condition
        #    perform an iteration
        #    save the information need it for the satistics
        
        # return the results and the info for statistics
        f = []
        stats = []
        self._last_stats = []
        for i in range(0, self._numberOfIterations):
            self.iteration()
            if args == self._numberOfSeeds - 1:
                self._last_stats.append(self._repository.computeAverageFitnessAndDeviation())
        #print(stats)
        '''
        for i in stats:
            f.append(i[0])
        '''

        self._statistics.append(self._repository.computeBestFitnessLastestPopulation())
        #self._statistics.append([np.average(f), np.std(f)])

    
    
    def solver(self, args=0):
        start_time = time.time()
        # args - list of parameters needed in order to run the solver
        # create the population,
        # run the algorithm
        # return the results and the statistics
        for i in range(self._numberOfSeeds):
            seed(30 - i)
            population = self._repository.createPopulation([self._populationSize, self._stepsNb])
            self._repository.addPopulation(population)
            self.run(i)
            #print(self._statistics[i])

        print("--- %.2f seconds ---" % (time.time() - start_time))
        return self._repository.getFirstPath(), self._statistics, self._last_stats


    def mapWithDrone(self, mapImage):
        drona = pygame.image.load("drona.png")
        mapImage.blit(drona, (0,  0))

        return mapImage

    def customSort(self, open, totalDistance):
        list = open
        ordered = False
        while ordered == False:
            ordered = True
            for i in range(0, len(list) - 1):
                if totalDistance[list[i][0]][list[i][1]] > totalDistance[list[i + 1][0]][list[i + 1][1]]:
                    aux = list[i]
                    list[i] = list[i + 1]
                    list[i + 1] = aux
                    ordered = False
        return list

    def searchAStar(self, mapM, initialX, initialY, finalX, finalY):
        dlin = [-1, 0, 1, 0]
        dcol = [0, 1, 0, -1]

        open = []
        closed = []
        parent = {}

        start = [initialX, initialY]
        end = [finalX, finalY]
        parent[str(start)] = None
        parent[str(end)] = None

        totalDistance = np.zeros((mapM.n, mapM.m))
        startDistance = np.zeros((mapM.n, mapM.m))
        goalDistance = np.zeros((mapM.n, mapM.m))
        open.append(start)
        while len(open) > 0:
            open = self.customSort(open, totalDistance)

            current = open.pop(0)

            closed.append(current)
            #print(parent)

            if current == end:
                path = []
                while current != start:
                    #print(current)
                    path.append([current[0], current[1]])
                    current = parent[str(current)]
                path.append([current[0], current[1]])
                #print(path)
                return path[::-1]

            for i in range(0, 4):
                new_pos = [current[0] + dlin[i], current[1] + dcol[i]]
                if new_pos not in closed and new_pos[0] >= 0 and new_pos[0] < mapM.n and new_pos[1] >= 0 and new_pos[1] < mapM.m:
                    if mapM.surface[new_pos[0]][new_pos[1]] == 0 or mapM.surface[new_pos[0]][new_pos[1]] == 2:
                        parent[str(new_pos)] = current
                        startDistance[new_pos[0]][new_pos[1]] = startDistance[current[0]][current[1]]
                        goalDistance[new_pos[0]][new_pos[1]] = 1
                        totalDistance[new_pos[0]][new_pos[1]] = startDistance[new_pos[0]][new_pos[1]] + goalDistance[new_pos[0]][new_pos[1]]

                        if self.add_open(open, new_pos, totalDistance):
                            open.append(new_pos)
        return None

    def add_open(self, open, new_node, totalDistance):
        for node in open:
            if node == new_node and totalDistance[node[0]][node[1]] <= totalDistance[new_node[0]][new_node[1]]:
                return False
        return True