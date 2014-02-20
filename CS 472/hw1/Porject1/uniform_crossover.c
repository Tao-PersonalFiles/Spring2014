/* two_point_crossover.c
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define X_I 30		// define the number of values for each solution

void crossover(double father[X_I], 
		double mother[X_I], double children[2][X_I])
{
	double tmp= 0;
	int P = 0;
	int i;
	for(i = 0; i < X_I; i++) {
		if((P = rand()%100) < 30) {
			tmp = children[0][i];
			children[0][i] = children[1][i];
			children[1][i] = tmp;
		}
	}
}