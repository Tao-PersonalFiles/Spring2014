/* one_point_crossover.c
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define X_I 30		// define the number of values for each solution

void crossover(double father[X_I], 
		double mother[X_I], double children[2][X_I])
{
	int cut = rand()%X_I;
	int i;
	for(i = 0; i < cut; i++){
		children[0][i] = father[i];
		children[1][i] = mother[i];
	}
	for( ; i < X_I; i++){
		children[1][i] = father[i];
		children[0][i] = mother[i];
	}
}