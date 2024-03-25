import matplotlib.pyplot as plt

# Initialize empty lists to store extracted data
special_characters_count = []
scores = []

# Open the file containing filtered data for reading
with open('filtered_data_score_rockyou.txt', 'r') as file:
    # Read each line
    for line_number, line in enumerate(file, start=1):
        # Split the line to extract password and score
        parts = line.split(',')
        # Ensure the line has the expected structure
        if len(parts) == 2:
            password = parts[0].strip()
            special_character_count = sum(1 for char in password if not char.isalnum())  # Count special characters
            score = int(parts[1].split(':')[1].strip())
            # Append data to lists
            special_characters_count.append(special_character_count)
            scores.append(score)
        else:
            print(f"Warning: Unable to parse line {line_number}. Skipping.")

# Plotting
plt.figure(figsize=(8, 6))
plt.scatter(special_characters_count, scores, color='blue')
plt.title('RockYou: Number of Special Characters vs Score')
plt.xlabel('Number of Special Characters')
plt.ylabel('Score')
plt.grid(True)

# Set y-axis ticks to start at 0 and increment by 1
plt.yticks(range(max(scores) + 1))

plt.tight_layout()

# Save the plot as an image file
plt.savefig('special_characters_score_scatter_rockyou.png')

# Show the plot
plt.show()

# import matplotlib.pyplot as plt

# # Initialize empty dictionaries to store counts and scores
# special_characters_counts = {}
# scores = {}

# # Open the file containing filtered data for reading
# with open('filtered_data_score_rockyou.txt', 'r') as file:
#     # Read each line
#     for line_number, line in enumerate(file, start=1):
#         # Split the line to extract password and score
#         parts = line.split(',')
#         # Ensure the line has the expected structure
#         if len(parts) == 2:
#             password = parts[0].strip()
#             special_character_count = sum(1 for char in password if not char.isalnum())  # Count special characters
#             score = int(parts[1].split(':')[1].strip())
#             # Update dictionaries
#             special_characters_counts[special_character_count] = special_characters_counts.get(special_character_count, 0) + 1
#             scores[special_character_count] = scores.get(special_character_count, 0) + score
#         else:
#             print(f"Warning: Unable to parse line {line_number}. Skipping.")

# # Convert dictionaries to lists for plotting
# special_characters_counts_list = list(special_characters_counts.keys())
# scores_list = [scores[count] / special_characters_counts[count] for count in special_characters_counts_list]

# # Plotting
# plt.figure(figsize=(8, 6))
# plt.bar(special_characters_counts_list, scores_list, color='blue')
# plt.title('RockYou: Number of Special Characters vs Average Score')
# plt.xlabel('Number of Special Characters')
# plt.ylabel('Average Score')
# plt.grid(True)

# plt.tight_layout()

# # Save the plot as an image file
# plt.savefig('special_characters_avg_score_bar_rockyou.png')

# # Show the plot
# plt.show()
