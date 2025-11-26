import sys
import matplotlib.pyplot as plt
import numpy as np
import os

def load_datasets(filepath):
    """
    Loads a CSV-like file where each line is:
        title, v1, v2, v3, ...
    Returns:
        titles (list of strings)
        datasets (list of numpy arrays)
    """
    if not os.path.exists(filepath):
        print(f"File not found: {filepath}")
        sys.exit(1)

    titles = []
    datasets = []

    with open(filepath, "r", encoding="utf-8") as f:
        for lineno, raw in enumerate(f.read().splitlines(), start=1):
            line = raw.strip()
            if not line:
                continue

            parts = [p.strip() for p in line.split(",")]
            title = parts[0] if parts[0] else f"Line{lineno}"

            values = []
            for p in parts[1:]:
                if p == "":
                    continue
                try:
                    values.append(float(p))
                except ValueError:
                    print(f"Warning: Skipping non-numeric value '{p}' on line {lineno}")

            if len(values) == 0:
                print(f"Warning: No numeric values in line {lineno} ({title})")
                datasets.append(np.array([]))
            else:
                datasets.append(np.array(values))

            titles.append(title)

    return titles, datasets


def plot_datasets(titles, datasets):
    """
    Plots each dataset as a separate line.
    No dots, thinner lines.
    """

    plt.figure(figsize=(12, 7))

    for title, data in zip(titles, datasets):
        if len(data) == 0:
            continue
        x = np.arange(len(data))
        plt.plot(x, data, linewidth=1, label=title)  # <-- no marker, thinner line

    plt.title("Dataset Values")
    plt.xlabel("Index")
    plt.ylabel("Value")
    plt.grid(True)
    plt.legend()
    plt.tight_layout()
    plt.show()


def main():
    
    titles, datasets = load_datasets("data4.csv")
    plot_datasets(titles, datasets)


if __name__ == "__main__":
    main()
