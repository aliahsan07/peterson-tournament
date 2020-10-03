#!/bin/bash
#SBATCH -J myMPI            # job name
#SBATCH -o myMPI.o%j        # output and error file name (%j expands to jobID)
#SBATCH -N 2                # number of nodes requested
#SBATCH -n 48               # total number of mpi tasks requested
#SBATCH -p development      # queue (partition) -- normal, development, etc.
#SBATCH -t 01:30:00         # run time (hh:mm:ss) - 1.5 hours

#SBATCH --mail-user=axa190023@utdallas.edu
#SBATCH --mail-type=begin   # email me when the job starts
#SBATCH --mail-type=end     # email me when the job finishes

# run the executable named a.out
javac Main.java
java Main 300 9