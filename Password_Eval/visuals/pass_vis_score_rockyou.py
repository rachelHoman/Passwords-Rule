import matplotlib.pyplot as plt

# Initialize empty lists to store extracted data
password_lengths = []
scores = []

# Open the file containing filtered data for reading
with open('filtered_data_score_rockyou.txt', 'r') as file:
    # Read each line
    for line_number, line in enumerate(file, start=1):
        # Split the line to extract password and score
        parts = line.split(',')
        # Ensure the line has the expected structure
        if len(parts) == 2:
            # Special handling for passwords with '$' sign
            if '$' in parts[0]:
                password = parts[0].replace('$', 'special_character')
                password_length = len(password) + 1  # Adding 1 to length count for '$' sign
                password = password.replace('special_character', '$')
            else:
                password = parts[0].split(':')[1].strip()
                password_length = len(password)
            #score = int(parts[1].rsplit(', Score:', 1)[1])
            score = int(parts[1].split(':')[1].strip())
            # Append data to lists
            password_lengths.append(password_length)
            scores.append(score)
        else:
            print(f"Warning: Unable to parse line {line_number}. Skipping.")

# Plotting
plt.figure(figsize=(8, 6))
plt.scatter(password_lengths, scores, color='blue')
plt.title('Rockyou: Password Length vs Score')
plt.xlabel('Password Length')
plt.ylabel('Score')
plt.grid(True)

# Set y-axis ticks to start at 0 and increment by 1
plt.yticks(range(max(scores) + 1))

plt.tight_layout()

# Save the plot as an image file
plt.savefig('password_length_score_scatter_rockyou.png')

# Show the plot
plt.show()
