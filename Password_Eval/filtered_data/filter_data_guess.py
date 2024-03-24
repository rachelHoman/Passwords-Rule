# Open the original file for reading
with open('myspace_output_pass.txt', 'r') as file:
    # Open a new file for writing
    with open('filtered_data_guesses.txt', 'w') as output_file:
        # Read each line
        for line_number, line in enumerate(file, start=1):
            # Check if the line contains both 'password' and 'crack_times_seconds'
            if "'password'" in line and "'guesses'" in line:
                try:
                    # Extract password and crack time seconds from the line
                    password_start = line.find("'password': ")
                    password_end = line.find(",", password_start)
                    password = line[password_start + len("'password': "):password_end]
                    guesses_start = line.find("'guesses': ")
                    guesses_end = line.find(",", guesses_start)
                    guesses = line[guesses_start + len("'guesses': "):guesses_end]
                    
                    # Write the necessary data to the new file
                    output_file.write(f"Password: {password.strip()}, Guesses: {guesses.strip()}\n")
                except Exception as e:
                    print(f"Warning: Unable to parse line {line_number}: {e}. Skipping.")
                    continue

print("Filtered data saved to 'filtered_data_guess.txt'")
