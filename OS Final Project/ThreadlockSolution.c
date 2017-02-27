#include <stdio.h>
#include <semaphore.h>
#include <pthread.h>

//number of times the philosophers (threads) can eat and a variable of the current turn
int total_num_eats = 100, exclusiveVar;
//array of pthread_locks for each thread
pthread_mutex_t chopstick[5];
//pthread condition variable for the locks
pthread_cond_t cond_var;

void *eat(void *i);


int main()
{
	int i;
	//initialize pthread_locks
	for(i = 0; i < 5; i++)
	{
		if(pthread_mutex_init(&chopstick[i], NULL) < 0)
		{
			fprintf(stderr, "Error: cold not initialize semaphore.");
			return -1;
		}
	}
	//initialiaze condition variable
	pthread_cond_init(&cond_var, NULL);
	
	//creat threads
	int params[5] = {0,1,2,3,4};
	pthread_t tid1, tid2, tid3, tid4, tid5;
	pthread_attr_t attr;
	pthread_attr_init(&attr);
	pthread_create(&tid1,&attr, eat, &params[0]);
	pthread_create(&tid2,&attr, eat, &params[1]);
	pthread_create(&tid3,&attr, eat, &params[2]);
        pthread_create(&tid4,&attr, eat, &params[3]);
	pthread_create(&tid5,&attr, eat, &params[4]);
	
	int index;
	//while the program is still eating (executing)
	while(total_num_eats > 0)
	{
		//loop through threads and release lock one by one form them
		for(index = 0; index < 5; index++)
		{
			pthread_mutex_lock(&chopstick[index]);
			exclusiveVar = index;
			pthread_cond_signal(&cond_var);
			pthread_mutex_unlock(&chopstick[index]);
		}
	}

	pthread_join(tid1, NULL);
	pthread_join(tid2, NULL);
	pthread_join(tid3, NULL);
	pthread_join(tid4, NULL);
	pthread_join(tid5, NULL);

	
	return 0;
}

void *eat(void *i)
{
	int index = *(int *)i;
	while(total_num_eats > 0)
	{
		//lock the mutex and wait for the turn to be this thread
		pthread_mutex_lock(&chopstick[index]);
		while(exclusiveVar != index)
		{
			pthread_cond_wait(&cond_var, &chopstick[index]);
		}
		
		//print the information and decrement the number of eats 
		printf("num eats remaining: %d\n", total_num_eats--);		
		printf("Philospher %d ate.\n", index+1);
		
		//unlock the mutex
		pthread_mutex_unlock(&chopstick[index]);

		sleep(1);
	}
}
