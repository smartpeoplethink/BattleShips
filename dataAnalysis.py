import numpy as np
import matplotlib.pyplot as plt
from statistics import multimode
import re

def sanitize_filename(name):
    return re.sub(r'[<>:"/\\|?*]', '_', name)

def analyze_datasets_from_csv(filename):
    """
    Reads a CSV file of datasets:
    Label, value1, value2, ...
    
    Produces:
    - full statistics
    - ONE centered graph per statistic (means, maxes, modes, etc.)
    X-axis = dataset labels
    Y-axis = statistic value
    All printed numeric outputs are clean (plain floats).
    """

    with open(filename, "r") as f:
        lines = f.read().strip().split("\n")

    titles     = []
    counts     = []
    sums       = []
    means      = []
    medians    = []
    modes      = []
    mins       = []
    maxs       = []
    ranges     = []
    stdev_s    = []
    stdev_p    = []
    vars_s     = []
    vars_p     = []
    q1s        = []
    q3s        = []
    iqrs       = []

    # -----------------------------------------
    # PARSE DATASETS
    # -----------------------------------------
    for line in lines:
        parts = [p.strip() for p in line.split(",")]
        title = parts[0]

        try:
            data = np.array([float(p) for p in parts[1:] if p != ""])
        except ValueError:
            print(f"Warning: invalid data in: {title}")
            data = np.array([])

        titles.append(title)

        if data.size == 0:
            counts.append(0)
            sums.append(None)
            means.append(None)
            medians.append(None)
            modes.append(None)
            mins.append(None)
            maxs.append(None)
            ranges.append(None)
            stdev_s.append(None)
            stdev_p.append(None)
            vars_s.append(None)
            vars_p.append(None)
            q1s.append(None)
            q3s.append(None)
            iqrs.append(None)
            continue

        counts.append(len(data))
        sums.append(float(data.sum()))
        means.append(float(data.mean()))
        medians.append(float(np.median(data)))
        m = multimode(data.tolist())
        modes.append(float(m[0]) if m else None)
        mins.append(float(data.min()))
        maxs.append(float(data.max()))
        ranges.append(float(data.max() - data.min()))
        stdev_s.append(float(data.std(ddof=1)) if len(data) > 1 else 0.0)
        stdev_p.append(float(data.std(ddof=0)))
        vars_s.append(float(data.var(ddof=1)) if len(data) > 1 else 0.0)
        vars_p.append(float(data.var(ddof=0)))
        q1s.append(float(np.percentile(data, 25)))
        q3s.append(float(np.percentile(data, 75)))
        iqrs.append(float(q3s[-1] - q1s[-1]))

    # -----------------------------------------
    # GRAPHING FUNCTION (centered vertically)
    # -----------------------------------------
    def make_graph(values, stat_name):
        if all(v is None for v in values):
            print(f"Skipping graph '{stat_name}' (no valid data).")
            return

        # Convert None → NaN for plotting
        clean_vals = [float(v) if v is not None else np.nan for v in values]

        # Determine center of graph
        avg = np.nanmean(clean_vals)
        spread = np.nanmax(clean_vals) - np.nanmin(clean_vals)
        if spread == 0:
            spread = 1  # avoid flat line issues

        padding = spread * 0.3  # extra vertical space

        ymin = avg - spread/2 - padding
        ymax = avg + spread/2 + padding

        plt.figure(figsize=(10, 5))
        plt.bar(titles, clean_vals)
        plt.xticks(rotation=45, ha='right')
        plt.title(f"{stat_name} of Datasets")
        plt.ylabel(stat_name)
        plt.ylim(ymin, ymax)
        plt.tight_layout()

        safe_name = sanitize_filename(stat_name)
        plt.savefig(f"{safe_name}.png")
        plt.close()

        print(f"Saved graph: {safe_name}.png")

    # -----------------------------------------
    # GENERATE ALL GRAPHS
    # -----------------------------------------
    stats = {
        "Mean": means,
        "Median": medians,
        "Mode": modes,
        "Min": mins,
        "Max": maxs,
        "Range": ranges,
        "Standard Deviation (Sample)": stdev_s,
        "Standard Deviation (Population)": stdev_p,
        "Variance (Sample)": vars_s,
        "Variance (Population)": vars_p,
        "Q1": q1s,
        "Q3": q3s,
        "IQR": iqrs,
    }

    for stat_name, values in stats.items():
        make_graph(values, stat_name)

    # -----------------------------------------
    # CLEAN PRINTED OUTPUT
    # -----------------------------------------
    print("titles:", ", ".join(titles))
    print("means:", [float(v) if v is not None else None for v in means])
    print("medians:", [float(v) if v is not None else None for v in medians])
    print("modes:", [float(v) if v is not None else None for v in modes])
    print("mins:", [float(v) if v is not None else None for v in mins])
    print("maxs:", [float(v) if v is not None else None for v in maxs])
    print("ranges:", [float(v) if v is not None else None for v in ranges])
    print("stdev_sample:", [float(v) if v is not None else None for v in stdev_s])
    print("stdev_population:", [float(v) if v is not None else None for v in stdev_p])
    print("variance_sample:", [float(v) if v is not None else None for v in vars_s])
    print("variance_population:", [float(v) if v is not None else None for v in vars_p])
    print("q1:", [float(v) if v is not None else None for v in q1s])
    print("q3:", [float(v) if v is not None else None for v in q3s])
    print("iqr:", [float(v) if v is not None else None for v in iqrs])


# Example:
analyze_datasets_from_csv("data3.csv")
