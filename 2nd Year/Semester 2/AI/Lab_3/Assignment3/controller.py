from repository import *
import time

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
        print("Iteration " + str(self._iteration))

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
        dev = []
        for i in range(0, self._numberOfIterations):
            self.iteration()
            stats.append(self._repository.computeAverageFitnessAndDeviation())
        #print(stats)
        for i in stats:
            f.append(i[0])

        self._statistics.append([np.average(f), np.std(f)])

    
    
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
            self.run()
            #print(self._statistics[i])
        print("--- %.2f seconds ---" % (time.time() - start_time))
        return self._repository.getFirstPath(), self._statistics


    def mapWithDrone(self, mapImage):
        drona = pygame.image.load("drona.png")
        mapImage.blit(drona, (0,  0))

        return mapImage