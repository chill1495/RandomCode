#include <stdio.h>
#include <semaphore.h>
#include <pthread.h>

//total number of times the philosopher threads loop
int total_num_eats = 100;
//array of semaphores, one for each thread
sem_t chopstick[5];

void *eat(void *i);


int main()
{
	int i;
	//initialize all semaphores
	for(i = 0; i < 5; i++)
	{
		if(sem_init(&chopstick[i], 1,1) < 0)
		{
			fprintf(stderr, "Error: cold not initialize semaphore.");
			return -1;
		}
	}
	//create threads with the appropriate index
	int params[5] = {0,1,2,3,4};
	pthread_t tid1, tid2, tid3, tid4, tid5;
	pthread_attr_t attr;
	pthread_attr_init(&attr);
	pthread_create(&tid1,&attr, eat, &params[0]);
	pthread_create(&tid2,&attr, eat, &params[1]);
	pthread_create(&tid3,&attr, eat, &params[2]);
        pthread_create(&tid4,&attr, eat, &params[3]);
	pthread_create(&tid5,&attr, eat, &params[4]);

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
		//wait for the current index semaphore(chopstick) and the following semaphore(chopstick)
		sem_wait(&chopstick[index]);
		sem_wait(&chopstick[(index +1) %5]);
		
		//print message and decrement total_num_eats
		printf("num eats remaining: %d\n", total_num_eats--);		
		printf("Philospher %d ate.\n", index+1);
		
		//post the 2 semaphores so the other threads can use them
		sem_post(&chopstick[index]);
		sem_post(&chopstick[(index +1) % 5]);

		sleep(1);
	}
}
