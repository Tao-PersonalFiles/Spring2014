#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <sys/stat.h>
#include <string.h>

#define INTERVAL 0.01
//#define range = 1024; // +-1024/200 

int counter;

void getNeighbours(double currentNode[30], double neighbors[60][30])
{
	int i = 0;
	int j = 0;
	//double plus =  0.01;
	//double minus= -0.01;
	for(i = 0; i< 30; i++)
	{
		for (j = 0; j < i; j++)
		{
			neighbors[i][j] = currentNode[j];
		}
		if(currentNode[j]+INTERVAL <= 5.12 && currentNode[j]+INTERVAL >= -5.12){
			neighbors[i][j] = (currentNode[j]) + INTERVAL;
		}
		for(j = i+1; j < 30; j++){
			neighbors[i][j] = currentNode[j];
		}
	}
	
	for(i = 30; i< 60; i++)
	{
		for (j = 0; j < i-30; j++)
		{
			neighbors[i][j] = currentNode[j];
		}
		if(currentNode[j]-INTERVAL <= 5.12 && currentNode[j]-INTERVAL >= -5.12){
			neighbors[i][j] = (currentNode[j]) - INTERVAL;
		}
		for(j = i+1-30; j < 30; j++){
			neighbors[i][j] = currentNode[j];
		}
	}

}

double evaluate(double x[30])
{
	double z = 0;
	double tmp;
	int i;
	for(i = 0 ; i < 30; i++ )
	{
		tmp = x[i];
		z += tmp*tmp;
	}

	return z;
}

double hillclimbing(double x[30], double resultA[30])
{
	double currentNode[30];
	memcpy(currentNode, x, sizeof(double)*30);
	double neighbors[60][30];

	double nextEval = 99999;
	double nextNode[30];
	double currentEval = evaluate(currentNode);
	printf("RandomEval: %lf\n", currentEval);
	double tmp;
	int i;

	//printf("Test %lf\n", currentEval);
	while(1)//currentEval > 0.0001)
	{
		getNeighbours(currentNode,neighbors);
		nextEval = 99999;
		for(i = 0; i < 60; i++)
		{
			tmp = evaluate(neighbors[i]);			
			if(tmp < nextEval){
				memcpy(nextNode, neighbors[i],sizeof(double)*30);
				nextEval = tmp;
			}
			counter++;
		}
		

		if (nextEval >= currentEval)
		{
			memcpy(resultA,currentNode,sizeof(double)*30);
			return currentEval;
		}
		memcpy(currentNode,nextNode,sizeof(double)*30);
		currentEval = nextEval;
		//printf("currentEval[%d]: %lf\n", counter, currentEval );
	}
}

int main()
{
	double result;
	double resultA[30];
	double randA[30];

	int i;

	srand(time(NULL));
	for(i = 0; i< 30; i++)
	{
		randA[i] = rand()%1024 / 100 - 5.12;
		//printf("Random[%d]: %lf\n",i, randA[i]);
	}

	counter = 0;
	result = hillclimbing(randA,resultA);

	/*
    for(i = 0; i < 30; i++){
    	printf("\nBSF[%d]: %lf", i , resultA[i]);
    }*/
    printf("BestEval: %lf\n", result);
    printf("Counter: %d\n", counter);
	
	return 0;
}
