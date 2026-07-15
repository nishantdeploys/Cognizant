import React from 'react';

function IndianPlayers() {
  const TeamIndia = ['Rohit', 'Gill', 'Virat', 'Rahul', 'Hardik', 'Jadeja'];
  const T20players = ['Surya', 'Pant', 'Shami'];
  const RanjiTrophyPlayers = ['Pujara', 'Iyer', 'Ashwin'];
  const mergedPlayers = [...T20players, ...RanjiTrophyPlayers];

  const [first, second, third, fourth, fifth, sixth] = TeamIndia;
  const oddTeamPlayers = [first, third, fifth].filter(Boolean);
  const evenTeamPlayers = [second, fourth, sixth].filter(Boolean);

  return (
    <div className="card">
      <h2>Indian Players</h2>

      <h3>Odd Team Players</h3>
      <ul>
        {oddTeamPlayers.map((player, index) => (
          <li key={`${player}-${index}`}>{player}</li>
        ))}
      </ul>

      <h3>Even Team Players</h3>
      <ul>
        {evenTeamPlayers.map((player, index) => (
          <li key={`${player}-${index}`}>{player}</li>
        ))}
      </ul>

      <h3>Merged Players</h3>
      <ul>
        {mergedPlayers.map((player, index) => (
          <li key={`${player}-${index}`}>{player}</li>
        ))}
      </ul>
    </div>
  );
}

export default IndianPlayers;
