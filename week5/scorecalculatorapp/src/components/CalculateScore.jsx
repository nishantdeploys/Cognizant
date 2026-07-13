function CalculateScore({ Name, School, Total, goal }) {
  const totalValue = Number(Total)
  const goalValue = Number(goal)

  const hasValidNumbers = Number.isFinite(totalValue) && Number.isFinite(goalValue) && goalValue > 0
  const averageScore = hasValidNumbers ? (totalValue / goalValue).toFixed(2) : 'N/A'

  return (
    <div className="result-card">
      <h2>Student Score Summary</h2>
      <p><strong>Name:</strong> {Name || 'N/A'}</p>
      <p><strong>School:</strong> {School || 'N/A'}</p>
      <p><strong>Total:</strong> {Total || 'N/A'}</p>
      <p><strong>Goal:</strong> {goal || 'N/A'}</p>
      <p><strong>Average Score:</strong> {averageScore}</p>
    </div>
  )
}

export default CalculateScore
