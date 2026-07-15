import React from 'react';

function ListofPlayers() {
  const players = [
    { name: 'Rohit', score: 88 },
    { name: 'Virat', score: 92 },
    { name: 'Rahul', score: 67 },
    { name: 'Gill', score: 75 },
    { name: 'Hardik', score: 58 },
    { name: 'Jadeja', score: 81 },
    { name: 'Pant', score: 64 },
    { name: 'Shami', score: 73 },
    { name: 'Bumrah', score: 95 },
    { name: 'Axar', score: 69 },
    { name: 'Siraj', score: 71 },
  ];

  const lowScorers = players.filter((player) => player.score < 70);

  return (
    <div className="card">
      <h2>List of Players</h2>
      <ul>
        {players.map((player, index) => (
          <li key={`${player.name}-${index}`}>
            {player.name} - {player.score}
          </li>
        ))}
      </ul>

      <h3>Players with Score Below 70</h3>
      <ul>
        {lowScorers.map((player, index) => (
          <li key={`${player.name}-${index}`}>
            {player.name} - {player.score}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ListofPlayers;
