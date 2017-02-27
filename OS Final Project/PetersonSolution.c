#include <stdio.h>
#include <semaphore.h>
#include <pthread.h>
#include <stdbool.h>

//number of times for the philosophers (threads) to eat
int total_num_eats = 100;
//array of the current position of each thread, the highest number is the next to go
int flags[5] = {-1,-1,-1,-1,-1};
//array of the turns of the threads, whichever is at the end is next
int turn[4] = {0,0,0,0};

void *eat(void *i);


int main()
{
	//create threads with correct index
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
		//loop through the threads
		int count;
		bool higherValue = true;
		for(count = 0; count < 4; count++)
		{
			flags[index] = count;
			turn[count] = index;
			
			//stay here while until it is this threads turn and there is no higher value in the flags array
			while(turn[count] = index && higherValue)
			{
				higherValue = false;
				int j;
				for(j = 0; j < 5; j++)
				{
					if(j != index && flags[j] > count)
					{
						higherValue = true;
					}
				}
			}
		}

		printf("num eats remaining: %d\n", total_num_eats--);		
		printf("Philospher %d ate.\n", index+1);
		//reset flags at the current index
		flags[index] = -1;

		sleep(1);
	}
}
