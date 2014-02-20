#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <sys/stat.h>
#include <string.h>

#define INTERVAL 0.01
#define P_RANGE	 511.97
#define N_RANGE	 -512.03

int counter;
int c;

double evaluate(double x[30])
{
	double z = 0;
	double tmp;
	int i;
	for(i = 0 ; i < 30; i++ )
	{
		tmp = x[i];
		z += tmp*sin(sqrt(abs(tmp)));
	}
	z += 418.9829*30;

	return z;
}

void getRandomNeighbor(double s[30], double snext[30])
{
	int rr;
	int num = rand()%30;
	int i;
	double plus;
	memcpy(snext, s,sizeof(double)*30);
	for(i = 0; i < num; i++){
		rr = rand()%30;
		plus = (rand()%10 < 5? -1 : 1)*((double)((double)100/(double)(10^(rand()%5))));
		if(s[rr] + plus <= P_RANGE && s[rr] + plus >= N_RANGE){
			snext[rr] = s[rr] + plus;
		}
	}

}

double probability(double e, double enext, double T)
{
	double p;
	p = exp((enext-e)/T);

	return p;
}

double simulatedAnnealing(double ss[30])
{
	double s[30];
	memcpy(s,ss,sizeof(double)*30);
	int i;

	double sbest[30];
	double snext[30];
	double e = evaluate(s);
	printf("RandomEval: %lf\n", e);
	memcpy(sbest,s,sizeof(double)*30);
	double ebest = e;
	double enext;
	double T;
	for(T = 100; T > 0; T -= 0.01){
		getRandomNeighbor(s, snext);
		enext = evaluate(snext);
		counter++;
		if(ebest > enext){
			memcpy(sbest, snext,sizeof(double)*30);
			ebest = enext;
		}else if(probability(e, enext, T) > ((double)rand() / ((double)(RAND_MAX)+(double)(1)))){
			memcpy(s,snext,sizeof(double)*30);
      		e = enext;
      		c++;
		}
	}
	//printf("test: %lf\n", T);

	memcpy(ss, sbest,sizeof(double)*30);
	return ebest;
}


int main()
{
	double result;
	double sbest[30];
	double ss[30];

	int i;

	srand(time(NULL));
	for(i = 0; i< 30; i++)
	{
		ss[i] = rand()%102400/100 + N_RANGE;
		printf("Random[%d]: %lf\n",i, ss[i]);
	}

	counter = 0;
	c = 0;
	result = simulatedAnnealing(ss);

	
    for(i = 0; i < 30; i++){
    	printf("\nBSF[%d]: %lf", i , ss[i]);
    }
    printf("\nBestEval: %lf\n", result);
    printf("Counter: %d\n", counter);
	printf("c: %d\n", c);
	return 0;
}
