//
//  main.c
//  genetic
//
//  Created by 张涛 on 14-2-11.
//  Copyright (c) 2014年 张涛. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <sys/stat.h>
#include <string.h>

#define INTERVAL 0.001	// difference we change in mutation 
#define P_RANGE 2.048	// maximun in range
#define N_RANGE	-2.048	// minimun in range

#define POP 100		// define the population as 1000
#define X_I 30		// define the number of values for each solution
#define GOOD 0 		// define for winner
#define POOR 1 		// define for loser

//#define

/*
// fitness function of Sphere
double evaluate(double x[X_I])	
{
	double z = 0;
	double tmp;
	int i;
	for(i = 0 ; i < X_I; i++ )
	{
		tmp = x[i];
		z += tmp*tmp;
	}
    
	return z;
}
*/
double evaluate(double x[X_I])
{
	double z = 0;
	int i;
	for(i = 0; i < X_I - 1; i++) {
		z += 100*pow(x[i+1]-pow(x[i],2) , 2) + pow(x[i] - 1, 2);
	}
}

int selection(int good_poor, double fitness[POP])
{
	int winner = rand()%POP;
	double winner_fitness = fitness[winner];
	int temp;
    
	int i;
	int N = rand()%POP;
	for(i = 0; i < N; i++){
		temp = rand()%POP;
		if(good_poor == GOOD && fitness[temp] < winner_fitness){		// this is for winner
			winner_fitness = fitness[temp];
			winner = temp;
		}else if(good_poor == POOR && fitness[temp] > winner_fitness){	// this is for loser
			winner_fitness = fitness[temp];
			winner = temp;
		}
	}
    
	return winner;
}

// void crossover_permutation

void mutate(double child[X_I])
{
	int i;
	for(i = 0; i < X_I; i++) {
		if(rand()%10 < 5){
			child[i] += (rand()%10 < 5 ? -1 : 1)*INTERVAL;
		}
	}
    
}

double getSum(double fitness[POP])
{
	int i = 0;
	double z;
	for(; i < POP; i++){
		z += fitness[i];
	}
    
	return z;
}

double GeneticAlgorithm(double resultA[X_I])
{
	srand(time(NULL));
 	double population[POP][X_I];
 	double fitness[POP];
    
 	int father;
 	int mother;
 	double children[2][X_I];
    
 	int loser1, loser2;

 	int best = -1;
 	double bestEval = 9999;
 	double AVG_fitness = 9999;
    
 	int i;
 	int j;
    // produce a population
 	for(i = 0; i < POP; i++){
 		for(j = 0; j < X_I; j++){
 			population[i][j] = (double)(rand()%((int)((P_RANGE - N_RANGE)*1000))) / 1000.00;
 		}
 		fitness[i] = evaluate(population[i]);	// calc all fitness
 		if(fitness[i] < bestEval){
 			bestEval = fitness[i];
 			// best = i;
 		}
 	}

 	printf("First Best Fitness: %f\n\n", bestEval);
    
 	AVG_fitness = getSum(fitness) / POP;
 	printf("First AVG_fitness: %f\n\n", AVG_fitness);

 	int count = 0;
 	while(count < POP*100){
		father = selection(GOOD, fitness);
		while((mother = selection(GOOD, fitness)) == father){
			// do nothing
			// Just make sure father != mother
		}
        
		//cossover to get children
		crossover(population[father],population[mother],children);
        
		//mutation children
		mutate(children[0]);
		mutate(children[1]);
        
		//replace children to the poorest two select by Tournament
        // find two losers first
		loser1 = selection(POOR, fitness);
		while((loser2 = selection(POOR, fitness)) == loser1){
			// do nothing
			// just make sure loser1 != loser2
		}
        // replace losers
		memcpy(population[loser1], children[0],sizeof(double)*30);
		fitness[loser1] = evaluate(children[0]);
		memcpy(population[loser2], children[1],sizeof(double)*30);
		fitness[loser2] = evaluate(children[1]);

		if(count % 100 == 0 ){
			AVG_fitness = getSum(fitness) / POP;
 			printf("AVG_fitness[%d]: %f\n\n", count, AVG_fitness);
		}
		
 		count++;
 	}
	//printf("Father[%d]: %lf\n", father, fitness[father]);
	//printf("Mother[%d]: %lf\n", mother, fitness[mother]);
 	
 	best = -1;
 	bestEval = 9999;
 	for(i = 0; i < POP; i++){
 		if(fitness[i] < bestEval){
 			bestEval = fitness[i];
 			best = i;
 		}
 	}

 	AVG_fitness = getSum(fitness) / POP;
 	printf("Final AVG_fitness: %f\n\n", AVG_fitness);
    
 	memcpy(resultA,population[best],sizeof(double)*X_I);
    
 	return bestEval;
    
}

int main()
{
	double result;
 	double resultA[X_I];
 	
 	result = GeneticAlgorithm(resultA);
    /*
 	int i;
 	for(i = 0; i < X_I; i++){
 		printf("%d: %f\n", i, resultA[i]);
 	}*/
    
    printf("Best Eval: %f\n", result);
    
 	return 0;
}
