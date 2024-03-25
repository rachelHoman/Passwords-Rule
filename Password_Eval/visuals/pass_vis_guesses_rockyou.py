import re
import matplotlib.pyplot as plt

# Initialize empty lists to store extracted data
password_lengths = []
guesses = []

# Open the file containing filtered data for reading
with open('filtered_data_guesses_rockyou.txt', 'r') as file:
    # Read each line
    for line_number, line in enumerate(file, start=1):
        # Extract password and guesses using regular expression
        match = re.match(r"Password: '(.+)', Guesses: Decimal\('(\d+)'\)", line.strip())
        if match:
            password = match.group(1)
            password_length = len(password)
            guess = int(match.group(2))
            # Append data to lists
            password_lengths.append(password_length)
            guesses.append(guess)
        else:
            print(f"Warning: Unable to parse line {line_number}: {line.strip()}. Skipping.")

# Check if any data was successfully parsed
if not password_lengths:
    print("Error: No valid data found in the file.")
    exit()

# Plotting
plt.figure(figsize=(8, 6))
plt.scatter(password_lengths, guesses, color='blue')
plt.title('Rockyou: Password Length vs Guesses')
plt.xlabel('Password Length')
plt.ylabel('Guesses')
plt.grid(True)

# Set y-axis scale to logarithmic
plt.yscale('log')

plt.tight_layout()

# Save the plot as an image file
plt.savefig('password_length_guesses_scatter_rockyou.png')

# Show the plot
plt.show()
